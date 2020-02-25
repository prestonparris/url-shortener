package com.prestonparris.urlshortener.service;

import com.prestonparris.urlshortener.repository.KeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyService {
    private static final Logger logger = LoggerFactory.getLogger(KeyService.class);

    @Autowired
    private KeyRepository keyRepository;

    public String getKey() {
        String newKey = keyRepository.getNextUnusedKey();

        boolean used = keyRepository.keyInUse(newKey);

        if (used) {
            // TODO: retry get key, this would be needed in a potential conflict across multiple url service workers
        }

        keyRepository.saveUsedKey(newKey);
        keyRepository.removeUnusedKey(newKey);

        return newKey;
    }
}
