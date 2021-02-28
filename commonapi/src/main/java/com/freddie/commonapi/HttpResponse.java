package com.freddie.commonapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class HttpResponse {
    private final Map<String, List<String>> headers = new HashMap<>();
    private String redirectUrl = "";
    private String body = "";

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
