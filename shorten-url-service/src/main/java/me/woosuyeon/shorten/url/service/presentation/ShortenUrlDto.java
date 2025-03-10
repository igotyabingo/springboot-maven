package me.woosuyeon.shorten.url.service.presentation;

import jakarta.validation.constraints.NotNull;
import me.woosuyeon.shorten.url.service.domain.ShortenUrl;

public class ShortenUrlDto {
    private Long id;
    private String key;
    @NotNull
    private String OriginalUrl;
    private Long RedirectedCount;


    public ShortenUrlDto(Long id, String key, String originalUrl, Long redirectedCount) {
        this.id = id;
        this.key = key;
        OriginalUrl = originalUrl;
        RedirectedCount = redirectedCount;
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

    public static ShortenUrl toEntity(ShortenUrlDto shortenUrlDto) {
        return new ShortenUrl(
                shortenUrlDto.getId(), shortenUrlDto.getKey(), shortenUrlDto.getOriginalUrl(), shortenUrlDto.getRedirectedCount()
        );

    }

    public static ShortenUrlDto toDto(ShortenUrl shortenUrl) {
        return new ShortenUrlDto(
                shortenUrl.getId(), shortenUrl.getKey(), shortenUrl.getOriginalUrl(), shortenUrl.getRedirectedCount()
        );
    }

}
