package com.freddie.commonapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class Http {

    protected final HttpURLConnection connection;

    protected final HttpParameter parameter = new HttpParameter();
    private final HttpResponse response = new HttpResponse();

    protected Http(String url) throws IOException {
        URL requestUrl = new URL(url);
        connection = (HttpURLConnection) requestUrl.openConnection();
    }

    protected void setRequestHeaders() {
        if (parameter.getRequestHeaders() != null) {
            for (Map.Entry<String, String> header : parameter.getRequestHeaders().entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }
    }

    protected void setConnectionTimeout() {
        if (parameter.getConnectionTimeout() != 0) {
            connection.setConnectTimeout(parameter.getConnectionTimeout());
        }
    }

    protected void setReadTimeout() {
        if (parameter.getReadTimeout() != 0) {
            connection.setReadTimeout(parameter.getReadTimeout());
        }
    }

    protected void setRequestBody() throws IOException {
        if (parameter.getRequestBody() != null) {
            connection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(parameter.getRequestBody());
            outputStream.flush();
            outputStream.close();
        }
    }

    protected void setFollowRedirects() {
        if (!parameter.getFollowRedirects()) {
            connection.setInstanceFollowRedirects(parameter.getFollowRedirects());
        }
    }

    protected void retrieveResponseHeaders() {
        Map<String, List<String>> headers = connection.getHeaderFields();
        if (headers != null) {
            response.getHeaders().putAll(headers);
        }
    }

    public Map<String, List<String>> getResponseHeaders() {
        return response.getHeaders();
    }

    protected void retrieveRedirectUrl() {
        List<String> locationHeader = connection.getHeaderFields().get("Location");
        if (locationHeader != null) {
            response.setRedirectUrl(locationHeader.get(0));
        }
    }

    public String getRedirectUrl() {
        return response.getRedirectUrl();
    }

    public String getResponseBody() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();
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
