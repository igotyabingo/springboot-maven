package me.woosuyeon.shorten.url.service.domain;

import java.net.MalformedURLException;
import java.net.URL;

public class ShortenUrl {
    private String key;
    private String originalUrl;
    private Long redirectedCount = 0L;

    public ShortenUrl(String key) {
        this.key = key;
    }

    public ShortenUrl(String key, String originalUrl){
        try {
            new URL(originalUrl);
            this.key = key;
            this.originalUrl = originalUrl;
        } catch (MalformedURLException e) {
            throw new UrlException(e.getMessage());
        }

    }

    public ShortenUrl(String key, String originalUrl, Long redirectedCount) {
        this.key = key;
        this.originalUrl = originalUrl;
        this.redirectedCount = redirectedCount;
    }

    public void updateCount() {
        redirectedCount = redirectedCount + 1;
    }

    public String getKey() {
        return key;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public Long getRedirectedCount() {
        return redirectedCount;
    }
}
