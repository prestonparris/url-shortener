package com.prestonparris.urlshortener.repository;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Repository
public class KeyRepository {

    // TODO: in a real production system this would be backed by a distributed key-value store such as cassandra instead
    // where used and unused keys would be tables and consistent hashing would be used to distribute the keys across nodes by shortUrl

    private Set<String> unusedKeys = new HashSet<>();
    private Set<String> usedKeys = new HashSet<>();

    KeyRepository() {
        // TODO: this is just temp data, real data would come from the incomplete
        //  key generation script used to initially populate the key db
        unusedKeys.add("a1b2c3");
        unusedKeys.add("a2b1c3");
        unusedKeys.add("a3b3c3");
        unusedKeys.add("a4b2c3");
        unusedKeys.add("a5b3c3");
        unusedKeys.add("a6b7c3");
    }

    public String getNextUnusedKey() {
        Iterator<String> iter = unusedKeys.iterator();

        if (!iter.hasNext()) {
            throw new RuntimeException("All keys have been exhausted!");
        }

        return iter.next();
    }

    public boolean keyInUse(String key) {
        return usedKeys.contains(key);
    }

    public void saveUsedKey(String key) {
        usedKeys.add(key);
    }

    public void removeUsedKey(String key) {
        usedKeys.remove(key);
    }

    public void saveUnusedKey(String key) {
        unusedKeys.add(key);
    }

    public void removeUnusedKey(String key) {
        unusedKeys.remove(key);
    }

}
