package com.prestonparris.urlshortener;
import com.prestonparris.urlshortener.repository.KeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KeyGenerator implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(KeyGenerator.class);

    @Autowired
    KeyRepository keyRepository;

    @Override
    public void run(String... args) throws Exception {

        // TODO
        // generate all unique combinations of 6 character keys, save into key repo
        // 6 (characters per key) * 68.7B (unique keys) = 412 GB

        // loop over combinations
           // keyRepository.saveUnusedKey(key);

    }
}
