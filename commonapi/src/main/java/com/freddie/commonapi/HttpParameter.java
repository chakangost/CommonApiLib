package com.freddie.commonapi;

import java.util.HashMap;
import java.util.Map;

final class HttpParameter {
    private final Map<String, String> requestHeaders = new HashMap<>();
    private int connectionTimeout;
    private int readTimeout;
    private String requestBody;
    private boolean followRedirects;

    public String getRequestHeader(String key) {
        return requestHeaders.get(key);
    }

    public void setRequestHeader(String key, String value) {
        requestHeaders.put(key, value);
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders.putAll(requestHeaders);
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public boolean getFollowRedirects() {
        return followRedirects;
    }

    public void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }
}
