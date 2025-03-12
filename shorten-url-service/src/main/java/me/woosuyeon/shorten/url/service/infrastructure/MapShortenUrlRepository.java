package me.woosuyeon.shorten.url.service.infrastructure;

import me.woosuyeon.shorten.url.service.domain.EntityNotFoundException;
import me.woosuyeon.shorten.url.service.domain.ShortenUrl;
import me.woosuyeon.shorten.url.service.domain.ShortenUrlRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapShortenUrlRepository implements ShortenUrlRepository {
    private Map<String, ShortenUrl> urlList = new ConcurrentHashMap<>();

    public void add(ShortenUrl shortenUrl) {

        urlList.put(shortenUrl.getKey(), shortenUrl);
    }

    public ShortenUrl findByKey(String key) {
        ShortenUrl shortenUrl = urlList.get(key);
        return shortenUrl;
    }

    public ShortenUrl updateRedirectedCount(String key) {
        ShortenUrl shortenUrl = this.findByKey(key);

        if (null == shortenUrl) {
            throw new EntityNotFoundException("존재하지 않는 단축 URL입니다.");
        }

        shortenUrl.updateCount();
        return shortenUrl;
    }
}
