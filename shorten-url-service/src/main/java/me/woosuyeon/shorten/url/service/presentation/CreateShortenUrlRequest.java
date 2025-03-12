package me.woosuyeon.shorten.url.service.presentation;

import jakarta.validation.constraints.NotNull;

public class CreateShortenUrlRequest {
    @NotNull
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public CreateShortenUrlRequest(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
