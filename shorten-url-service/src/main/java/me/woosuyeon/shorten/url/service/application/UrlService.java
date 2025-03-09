package me.woosuyeon.shorten.url.service.application;

import me.woosuyeon.shorten.url.service.domain.ShortenUrl;
import me.woosuyeon.shorten.url.service.infrastructure.ListShortenUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private ListShortenUrlRepository listShortenUrlRepository;

    @Autowired
    UrlService (ListShortenUrlRepository listShortenUrlRepository) {
        this.listShortenUrlRepository = listShortenUrlRepository;
    }

    public String createNewKey(String originalUrl) {
        // 키 생성 알고리즘을 이용해 8자리 문자 키를 생성하고 리포지터리에 새 레코드 저장 후 리턴한다.
        return "";
    }

    public void redirectToOriginalUrl(String key) {
        // 키를 리포지터리에서 찾아 original url 필드를 참조하여 리디렉트 시킨다.
    }

    public Long getCount(String key) {
        // 키를 리포지터리에서 찾아 count 필드를 리턴한다.
        return 0L;
    }

    private ShortenUrl findByKey(String key) {
        // 키를 리포지터리에서 찾아 ShortenUrl 객체를 반환한다.
        return new ShortenUrl();
    }

}
