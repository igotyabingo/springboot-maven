package me.woosuyeon.shorten.url.service.application;

import me.woosuyeon.shorten.url.service.domain.ExistingKeyException;
import me.woosuyeon.shorten.url.service.domain.ShortenUrl;
import me.woosuyeon.shorten.url.service.domain.ShortenUrlRepository;
import me.woosuyeon.shorten.url.service.presentation.ShortenUrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlService {
    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    UrlService (ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrlDto createNewKey(ShortenUrlDto shortenUrlDto) {
        // 키 생성 알고리즘을 이용해 8자리 문자 키를 생성하고 리포지터리에 새 레코드 저장 후 리턴한다.
        String createdKey = createCode();
        ShortenUrl shortenUrl = shortenUrlRepository.add(new ShortenUrl(createdKey, shortenUrlDto.getOriginalUrl()));
        return ShortenUrlDto.toDto(shortenUrl);
    }

    public String redirectToOriginalUrl(String key) {
        ShortenUrl shortenUrl = shortenUrlRepository.updateRedirectedCount(key);

        return shortenUrl.getOriginalUrl();
    }

    public Long getCount(String key) {
        // 키를 리포지터리에서 찾아 count 필드를 리턴한다.
        ShortenUrl shortenUrl = shortenUrlRepository.findByKey(key);
        return shortenUrl.getRedirectedCount();
    }

    private String createCode() {
        final int MAX_RETRY_COUNT = 5;
        int count = 0;

        while(count++ < MAX_RETRY_COUNT) {
            String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            SecureRandom RANDOM = new SecureRandom();
            StringBuilder key = new StringBuilder(8);

            for (int i=0; i<8; i++) {
                key.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
            }

            String createdKey = key.toString();
            if (null == shortenUrlRepository.findByKey(createdKey)) {
                return createdKey;
            }
        }

        throw new ExistingKeyException("단축 URL이 중복됩니다.");

    }

}
