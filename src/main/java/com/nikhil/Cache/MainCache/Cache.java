package com.nikhil.Cache.MainCache;

import com.nikhil.Cache.EvictionAlgorithms.EvictionAlgorithm;
import com.nikhil.Cache.Executers.KeyBasedExecutor;
import com.nikhil.Cache.StorageMechanisms.interfaces.CacheStorage;
import com.nikhil.Cache.StorageMechanisms.interfaces.DBStorage;
import com.nikhil.Cache.WritePolicies.WritePolicy;

import java.util.concurrent.CompletableFuture;

public class Cache<K, V> {
    private final CacheStorage<K, V> cacheStorage;
    private final DBStorage<K, V> dbStorage;
    private final WritePolicy<K, V> writePolicy;
    private final EvictionAlgorithm<K> evictionAlgorithm;
    private final KeyBasedExecutor keyBasedExecutor;


    /**
     * Construct the cache
     *
     * @param cacheStorage
     * @param dbStorage
     * @param writePolicy
     * @param evictionAlgorithm
     * @param numExecuters
     */
    public Cache(CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage, WritePolicy<K, V> writePolicy, EvictionAlgorithm<K> evictionAlgorithm, int numExecuters) {
        this.cacheStorage = cacheStorage;
        this.dbStorage = dbStorage;
        this.writePolicy = writePolicy;
        this.evictionAlgorithm = evictionAlgorithm;
        this.keyBasedExecutor = new KeyBasedExecutor(numExecuters);
    }

    public CompletableFuture<V> accessData(K key) {
        return keyBasedExecutor.submitTask(key, () -> {
            try {
                if (!cacheStorage.containsKey(key)) {
                    throw new Exception("Key not found in cache: " + key);
                }
                evictionAlgorithm.keyAccessed(key);
                return cacheStorage.get(key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletableFuture<Void> updateData(K key, V value) {
        return keyBasedExecutor.submitTask(key, () -> {
            try {
                if (cacheStorage.containsKey(key)) {
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                } else {
                    if (cacheStorage.size() >= cacheStorage.getCapacity()) {
                        K evictedKey = evictionAlgorithm.evictKey();
                        if (evictedKey != null) {
                            int currentIndex = keyBasedExecutor.getExecuterIndexForKey(key);
                            int evictionIndex = keyBasedExecutor.getExecuterIndexForKey(evictedKey);
                            if (currentIndex == evictionIndex) {
                                cacheStorage.remove(evictedKey);
                            } else {
                                keyBasedExecutor.submitTask(evictedKey, () -> {
                                    try {
                                        cacheStorage.remove(evictedKey);
                                        return null;
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }).join();
                            }
                        }
                    }
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                }
                return null;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void shutdown() {
        keyBasedExecutor.shutdown();
    }
}
