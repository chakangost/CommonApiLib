package com.freddie.commonapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public abstract class Http {

    protected HttpURLConnection connection;
    protected HttpsURLConnection httpSConnection;
    protected boolean isHttps;
    protected final HttpParameter parameter = new HttpParameter();
    private final HttpResponse response = new HttpResponse();

    protected Http(String url) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        URL requestUrl = new URL(url);
        if (url.contains("https")) {
            isHttps = true;
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCertificates, new java.security.SecureRandom());
            httpSConnection = (HttpsURLConnection) requestUrl.openConnection();
            httpSConnection.setSSLSocketFactory(sc.getSocketFactory());
            httpSConnection.setDoInput(true);
            httpSConnection.setUseCaches(false);
        } else {
            isHttps = false;
            connection = (HttpURLConnection) requestUrl.openConnection();
        }
    }

    TrustManager[] trustAllCertificates = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null; // Not relevant.
                }
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }
    };

    protected void setRequestHeaders() {
        if (parameter.getRequestHeaders() != null) {
            for (Map.Entry<String, String> header : parameter.getRequestHeaders().entrySet()) {
                if (isHttps) {
                    httpSConnection.setRequestProperty(header.getKey(), header.getValue());
                } else {
                    connection.setRequestProperty(header.getKey(), header.getValue());
                }
            }
        }
    }

    protected void setConnectionTimeout() {
        if (parameter.getConnectionTimeout() != 0) {
            if (isHttps) {
                httpSConnection.setConnectTimeout(parameter.getConnectionTimeout());
            } else {
                connection.setConnectTimeout(parameter.getConnectionTimeout());
            }
        }
    }

    protected void setReadTimeout() {
        if (parameter.getReadTimeout() != 0) {
            if (isHttps) {
                httpSConnection.setReadTimeout(parameter.getReadTimeout());
            } else {
                connection.setReadTimeout(parameter.getReadTimeout());
            }
        }
    }

    protected void setRequestBody() throws IOException {
        if (parameter.getRequestBody() != null) {
            DataOutputStream outputStream = null;
            if (isHttps) {
                httpSConnection.setDoOutput(true);
                outputStream = new DataOutputStream(httpSConnection.getOutputStream());
            } else {
                connection.setDoOutput(true);
                outputStream = new DataOutputStream(connection.getOutputStream());
            }

            outputStream.writeBytes(parameter.getRequestBody());
            outputStream.flush();
            outputStream.close();
        }
    }

    protected void setFollowRedirects() {
        if (!parameter.getFollowRedirects()) {
            if (isHttps) {
                httpSConnection.setInstanceFollowRedirects(parameter.getFollowRedirects());
            } else {
                connection.setInstanceFollowRedirects(parameter.getFollowRedirects());
            }
        }
    }

    protected void retrieveResponseHeaders() {
        Map<String, List<String>> headers;
        if (isHttps) {
            headers = httpSConnection.getHeaderFields();
        } else {
            headers = connection.getHeaderFields();
        }
        if (headers != null) {
            response.getHeaders().putAll(headers);
        }
    }

    public Map<String, List<String>> getResponseHeaders() {
        return response.getHeaders();
    }

    protected void retrieveRedirectUrl() {
        List<String> locationHeader;
        if (isHttps) {
            locationHeader = httpSConnection.getHeaderFields().get("Location");
        } else {
            locationHeader = connection.getHeaderFields().get("Location");
        }
        if (locationHeader != null) {
            response.setRedirectUrl(locationHeader.get(0));
        }
    }

    public String getRedirectUrl() {
        return response.getRedirectUrl();
    }

    public String getResponseBody() throws IOException {
        if (isHttps) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpSConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            httpSConnection.disconnect();
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            connection.disconnect();
        }

        return response.toString();
    }

    public abstract Http setHeader(String key, String value);

    public abstract Http setHeaders(Map<String, String> headers);

    public abstract Http setConnectionTimeout(int connectionTimeout);

    public abstract Http setReadTimeout(int readTimeout);

    public abstract Http setBody(String body);

    public abstract Http setFollowRedirects(boolean followRedirects);

    public abstract int execute() throws IOException;
}
