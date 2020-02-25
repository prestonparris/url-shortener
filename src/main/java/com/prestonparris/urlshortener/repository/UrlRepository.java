package com.prestonparris.urlshortener.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UrlRepository {

    // TODO: in a real production system this would be backed by a distributed key-value store such as cassandra instead
    // where urls would be a table and consistent hashing would be used to distribute the keys across nodes by shortUrl

    private Map<String, String> shortToLongUrls = new HashMap<>();
    private Map<String, String> longToShortUrls = new HashMap<>();

    public void saveUrl(String shortUrl, String longUrl) {
        if (shortToLongUrls.containsKey(shortUrl) || longToShortUrls.containsKey(longUrl)) {
            throw new RuntimeException("short url already saved!");
        }

        shortToLongUrls.put(shortUrl, longUrl);
        longToShortUrls.put(longUrl, shortUrl);
    }

    public String getLongUrl(String shortUrl) {
        return shortToLongUrls.getOrDefault(shortUrl, null);
    }

    public String getShortUrl(String longUrl) {
        return longToShortUrls.getOrDefault(longUrl, null);
    }

    public void removeAll() {
        shortToLongUrls.clear();
        longToShortUrls.clear();
    }

}
