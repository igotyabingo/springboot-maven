package me.woosuyeon.shorten.url.service.domain;

public class ShortenUrl {
    private Long id;
    private String key;
    private String OriginalUrl;
    private Long RedirectedCount = 0L;
}
