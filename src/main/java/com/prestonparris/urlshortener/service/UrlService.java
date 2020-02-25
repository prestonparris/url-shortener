package com.prestonparris.urlshortener.service;

import com.prestonparris.urlshortener.repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private static final Logger logger = LoggerFactory.getLogger(UrlService.class);

    @Autowired
    KeyService keyService;

    @Autowired
    UrlRepository urlRepository;

    public String createShortUrl(String longUrl) {

        String savedShortUrl = urlRepository.getShortUrl(longUrl);

        if (savedShortUrl != null) {
            return savedShortUrl;
        }

        String shortUrl = keyService.getKey();

        urlRepository.saveUrl(shortUrl, longUrl);

        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        String longUrl = urlRepository.getLongUrl(shortUrl);

        if (longUrl == null) {
            throw new RuntimeException("long url not found!");
        }

        return longUrl;
    }

}
