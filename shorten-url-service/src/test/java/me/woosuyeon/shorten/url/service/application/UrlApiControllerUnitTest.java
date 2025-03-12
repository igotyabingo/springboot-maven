package me.woosuyeon.shorten.url.service.application;

import me.woosuyeon.shorten.url.service.presentation.UrlApiController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @WebMvcTest(controllers = UrlApiController.class)
    public class UrlApiControllerUnitTest {
        @MockitoBean
        private UrlService urlService;

        @Autowired
        private MockMvc mockMvc;

    @Test
    @DisplayName("원래의 URL로 리다이렉트 되어야 한다.")
    void redirectTest() throws Exception {
        String expectedOriginalUrl = "https://www.naver.com";

        when(urlService.redirectToOriginalUrl(any())).thenReturn(expectedOriginalUrl);

        mockMvc.perform(get("/any-key"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", expectedOriginalUrl));
    }
}
