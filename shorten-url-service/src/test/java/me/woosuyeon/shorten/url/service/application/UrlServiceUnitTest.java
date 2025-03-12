package me.woosuyeon.shorten.url.service.application;

import me.woosuyeon.shorten.url.service.domain.ExistingKeyException;
import me.woosuyeon.shorten.url.service.domain.ShortenUrl;
import me.woosuyeon.shorten.url.service.domain.ShortenUrlRepository;
import me.woosuyeon.shorten.url.service.presentation.CreateShortenUrlRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlServiceUnitTest {
    @Mock
    private ShortenUrlRepository shortenUrlRepository;
    @InjectMocks
    private UrlService urlService;

    @Test
    @DisplayName("key 생성 시도가 중복에 의해 5회 이상 실패 시 ExistingKeyException이 던져져야 한다.")
    void handleCreateKeyTrialsFails() {
        when(shortenUrlRepository.findByKey(any())).thenReturn(new ShortenUrl(null, "https://anyurl.com"));

        Assertions.assertThrows(ExistingKeyException.class, () ->
        {
            urlService.createNewKey(new CreateShortenUrlRequest("https://www.naver.com"));
        });

    }
}
