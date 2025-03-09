package me.woosuyeon.shorten.url.service.infrastructure;

import me.woosuyeon.shorten.url.service.domain.ShortenUrl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListShortenUrlRepository {
    private List<ShortenUrl> urlList = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public ShortenUrl add(ShortenUrl shortenUrl) {
        shortenUrl.setId(sequence.getAndAdd(1L));

        urlList.add(shortenUrl);
        return shortenUrl;
    }

    public ShortenUrl findByKey(String key) {
        return urlList.stream()
                .filter(shortenUrl -> shortenUrl.sameKey(key))
                .findFirst()
                .orElseThrow(); // 예외 클래스 구현해야 함
    }

    public ShortenUrl updateRedirectedCount(String key) {
        ShortenUrl shortenUrl = this.findByKey(key);
        shortenUrl.updateCount();
        return shortenUrl;
    }
}
