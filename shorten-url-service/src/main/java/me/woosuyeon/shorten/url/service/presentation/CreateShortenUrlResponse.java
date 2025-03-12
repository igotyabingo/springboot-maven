package me.woosuyeon.shorten.url.service.presentation;

import me.woosuyeon.shorten.url.service.domain.ShortenUrl;

public class CreateShortenUrlResponse {
    private String originalUrl;
    private String key;

    public CreateShortenUrlResponse(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.key = shortenUrl.getKey();
    }

    public String getKey() {
        return key;
    }
}
