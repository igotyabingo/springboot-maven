package me.woosuyeon.shorten.url.service.presentation;

import jakarta.validation.Valid;
import me.woosuyeon.shorten.url.service.application.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UrlApiController {
    private UrlService urlService;
    @Autowired
    UrlApiController (UrlService urlService) {
        this.urlService = urlService;
    }

    // 1. 단축 URL 생성 API
    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public ResponseEntity<CreateShortenUrlResponse> createNewKey(@RequestBody @Valid CreateShortenUrlRequest request) {
        CreateShortenUrlResponse response = urlService.createNewKey(request);
        return ResponseEntity.ok().body(response);
    }

    // 2. 단축 URL 리다이렉트 API
    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String key) throws URISyntaxException{
        String redirectTo = urlService.redirectToOriginalUrl(key);

        URI redirectUri = new URI(redirectTo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    // 3. 단축 URL 정보 조회 API
    @RequestMapping(value = "/api/count/{key}", method = RequestMethod.GET)
    public ResponseEntity<Long> getCount(@PathVariable String key) {
        Long count = urlService.getCount(key);
        return ResponseEntity.ok().body(count);
    }

}
