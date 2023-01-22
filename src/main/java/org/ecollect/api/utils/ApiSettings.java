package org.ecollect.api.utils;

public class ApiSettings {

    private String authToken;
    private String baseUrl;
    private int httpTimeout; // in seconds

    public ApiSettings(String auth, String baseUrl, int timeout) {
        this.authToken = auth;
        this.baseUrl = baseUrl;
        httpTimeout = timeout | 10;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getHttpTimeout() {
        return httpTimeout;
    }
}
