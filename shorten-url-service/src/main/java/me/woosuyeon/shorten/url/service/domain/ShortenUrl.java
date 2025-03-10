package me.woosuyeon.shorten.url.service.domain;

import java.net.MalformedURLException;
import java.net.URL;

public class ShortenUrl {
    private Long id;
    private String key;
    private String OriginalUrl;
    private Long RedirectedCount = 0L;

    public ShortenUrl(String key) {
        this.key = key;
    }

    public ShortenUrl(String key, String originalUrl){
        try {
            new URL(originalUrl);
            this.key = key;
            OriginalUrl = originalUrl;
        } catch (MalformedURLException e) {
            throw new UrlException(e.getMessage());
        }

    }

    public ShortenUrl(Long id, String key, String originalUrl, Long redirectedCount) {
        this.id = id;
        this.key = key;
        OriginalUrl = originalUrl;
        RedirectedCount = redirectedCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void updateCount() {
        RedirectedCount = RedirectedCount + 1;
    }

    public Long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getOriginalUrl() {
        return OriginalUrl;
    }

    public Long getRedirectedCount() {
        return RedirectedCount;
    }

    public Boolean sameKey(String key) {
        return this.key.equals(key);
    }
}
