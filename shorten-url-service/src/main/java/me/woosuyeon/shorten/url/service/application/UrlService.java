package me.woosuyeon.shorten.url.service.application;

import me.woosuyeon.shorten.url.service.domain.ShortenUrl;
import me.woosuyeon.shorten.url.service.infrastructure.ListShortenUrlRepository;
import me.woosuyeon.shorten.url.service.presentation.ShortenUrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.security.SecureRandom;

@Service
public class UrlService {
    private ListShortenUrlRepository listShortenUrlRepository;

    @Autowired
    UrlService (ListShortenUrlRepository listShortenUrlRepository) {
        this.listShortenUrlRepository = listShortenUrlRepository;
    }

    public ShortenUrlDto createNewKey(ShortenUrlDto shortenUrlDto) {
        // 키 생성 알고리즘을 이용해 8자리 문자 키를 생성하고 리포지터리에 새 레코드 저장 후 리턴한다.
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder key = new StringBuilder(8);

        for (int i=0; i<8; i++) {
            key.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        String createdKey = key.toString();
        ShortenUrl shortenUrl = listShortenUrlRepository.add(new ShortenUrl(createdKey, shortenUrlDto.getOriginalUrl()));
        return ShortenUrlDto.toDto(shortenUrl);
    }

    public String redirectToOriginalUrl(String key) {
        ShortenUrl shortenUrl = listShortenUrlRepository.updateRedirectedCount(key);

        return shortenUrl.getOriginalUrl();
    }

    public Long getCount(String key) {
        // 키를 리포지터리에서 찾아 count 필드를 리턴한다.
        ShortenUrl shortenUrl = listShortenUrlRepository.findByKey(key);
        return shortenUrl.getRedirectedCount();
    }

}
