package com.nikhil.Cache.WritePolicies;

import com.nikhil.Cache.StorageMechanisms.interfaces.CacheStorage;
import com.nikhil.Cache.StorageMechanisms.interfaces.DBStorage;

public interface WritePolicy<K, V> {

    /**
     * Write key value to both cache and db storage respectively.
     *
     * @param key
     * @param value
     * @param cacheStorage
     * @param dbStorage
     */
    void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage);
}
