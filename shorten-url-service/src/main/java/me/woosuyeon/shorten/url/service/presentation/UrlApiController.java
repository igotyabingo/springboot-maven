package me.woosuyeon.shorten.url.service.presentation;

import me.woosuyeon.shorten.url.service.application.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlApiController {
    private UrlService urlService;
    @Autowired
    UrlApiController (UrlService urlService) {
        this.urlService = urlService;
    }

    // 1. 단축 URL 생성 API
    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public ResponseEntity<String> createNewKey(@RequestBody String originalUrl) {
        String createdKey = urlService.createNewKey(originalUrl);
        return ResponseEntity.ok().body(createdKey);
    }

    // 2. 단축 URL 리다이렉트 API
    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public void redirectToOriginalUrl(@PathVariable String key) {
        urlService.redirectToOriginalUrl(key);
    }

    // 3. 단축 URL 정보 조회 API
    @RequestMapping(value = "/api/count/{id}", method = RequestMethod.GET)
    public ResponseEntity<Long> getCount(@PathVariable Long id) {
        Long count = 0L;
        return ResponseEntity.ok().body(count);
    }

}
