package me.woosuyeon.shorten.url.service.presentation;

import me.woosuyeon.shorten.url.service.application.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UrlViewController {

    private UrlService urlService;

    UrlViewController(UrlService urlService) {
        this.urlService = urlService;
    }

    // 2. 단축 URL 리다이렉트 API
    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public String redirectToOriginalUrl(@PathVariable String key) {
        String redirectTo = urlService.redirectToOriginalUrl(key);
        // 저장할 때 부터 절대 경로를 입력해야 함 www.naver.com 이 아니라 https://www.naver.com
        return "redirect:"+redirectTo;
    }

}
