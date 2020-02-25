package com.prestonparris.urlshortener.view;

public class CreateUrlResponse {
    public boolean success;
    public String url;
    public CreateUrlResponse(boolean success, String url) {
        this.success = success;
        this.url = url;
    }
}
