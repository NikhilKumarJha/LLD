package com.nikhil.Cache.EvictionAlgorithms;

public interface EvictionAlgorithm<K> {
    void keyAccessed(K key) throws Exception;

    K evictKey() throws Exception;
}
