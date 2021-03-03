package com.freddie.commonapi;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public final class HttpDelete extends Http {

    public HttpDelete(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        super(url);
        if (isHttps) {
            httpSConnection.setRequestMethod(HttpRequestType.DELETE);
        } else {
            connection.setRequestMethod(HttpRequestType.DELETE);
        }
    }

    @Override
    public Http setHeader(String key, String value) {
        parameter.setRequestHeader(key, value);
        return this;
    }

    @Override
    public Http setHeaders(Map<String, String> headers) {
        parameter.setRequestHeaders(headers);
        return this;
    }

    @Override
    public Http setConnectionTimeout(int connectionTimeout) {
        parameter.setConnectionTimeout(connectionTimeout);
        return this;
    }

    @Override
    public Http setReadTimeout(int readTimeout) {
        parameter.setReadTimeout(readTimeout);
        return this;
    }

    @Override
    public Http setBody(String body) {
        throw new UnsupportedOperationException("Not supported for HTTP DELETE");
    }

    @Override
    public Http setFollowRedirects(boolean followRedirects) {
        parameter.setFollowRedirects(followRedirects);
        return this;
    }

    @Override
    public int execute() throws IOException {
        int responseCode;

        setRequestHeaders();
        setConnectionTimeout();
        setReadTimeout();
        setFollowRedirects();

        if (isHttps) {
            responseCode = httpSConnection.getResponseCode();
        } else {
            responseCode = connection.getResponseCode();
        }

        retrieveResponseHeaders();
        retrieveRedirectUrl();

        return responseCode;
    }
}
