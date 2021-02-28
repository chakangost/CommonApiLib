package com.freddie.commonapi;

import java.io.IOException;
import java.util.Map;

public class HttpPut extends Http {

    public HttpPut(String url) throws IOException {
        super(url);
        connection.setRequestMethod(HttpRequestType.PUT);
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
        parameter.setRequestBody(body);
        return this;
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
            setRequestBody();

            responseCode = connection.getResponseCode();

            retrieveResponseHeaders();
            retrieveRedirectUrl();

        return responseCode;
    }
}
