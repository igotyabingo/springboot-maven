package me.woosuyeon.shorten.url.service.application;

import me.woosuyeon.shorten.url.service.domain.EntityNotFoundException;
import me.woosuyeon.shorten.url.service.domain.UrlException;
import me.woosuyeon.shorten.url.service.presentation.CreateShortenUrlRequest;
import me.woosuyeon.shorten.url.service.presentation.CreateShortenUrlResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlServiceTest {
    @Autowired
    private UrlService urlService;

    // 각 로직마다 정상적인 입력 및 예외가 발생해야 하는 입력 모두 확인한다. - 3번 제외 (GET, 리디렉션 관련)
    @DisplayName("1. 올바른 URL에 대해 단축 요청을 하면 키 생성 후 리포지토리에 저장되어야 한다.")
    @Test
    void shortenUrlAndFindByKeyTest() {
        String requestedUrl = "https://www.naver.com";
        CreateShortenUrlResponse response = urlService.createNewKey(new CreateShortenUrlRequest(requestedUrl));
        String createdKey = response.getKey();

        String originalUrl = urlService.redirectToOriginalUrl(createdKey);
        assertTrue(originalUrl.equals(requestedUrl));
    }

    @DisplayName("2. 올바르지 않은 URL에 대해 단축 요청 시 UrlException이 던져져야 한다.")
    @Test
    void invalidShortenUrlRejected() {
        String requestUrl = "www.naver.com";

        assertThrows(UrlException.class, () -> {
            CreateShortenUrlResponse response = urlService.createNewKey(new CreateShortenUrlRequest(requestUrl));
        });
    }

    @DisplayName("3. 단축URL 실행시 해당 페이지로 리디렉트 하고 카운트 필드를 증가시켜야 한다.")
    @Test
    void redirectToOriginalURL() {
        String requestedUrl = "https://www.naver.com";
        CreateShortenUrlResponse response = urlService.createNewKey(new CreateShortenUrlRequest(requestedUrl));
        String createdKey = response.getKey();

        String originalUrl = urlService.redirectToOriginalUrl(createdKey);

        assertTrue(originalUrl.equals(requestedUrl));
        assertTrue(urlService.getCount(createdKey) == 1);
    }

    @DisplayName("4. 단축된적 없는 키에 대해 리디렉트 요청시 EntityNotFoundException이 던져져야 한다.")
    @Test
    void invalidKeyRedirectionRejected() {
        String key = "8E2Gm";

        assertThrows(EntityNotFoundException.class, () -> {
            urlService.redirectToOriginalUrl(key);
        });
    }

}