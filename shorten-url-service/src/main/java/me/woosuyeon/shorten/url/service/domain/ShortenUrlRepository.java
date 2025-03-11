package me.woosuyeon.shorten.url.service.domain;

public interface ShortenUrlRepository {
    ShortenUrl add(ShortenUrl shortenUrl);
    ShortenUrl findByKey(String key);
    ShortenUrl updateRedirectedCount(String key);
}
