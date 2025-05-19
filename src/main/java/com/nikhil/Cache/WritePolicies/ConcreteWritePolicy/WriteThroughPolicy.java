package com.nikhil.Cache.WritePolicies.ConcreteWritePolicy;

import com.nikhil.Cache.StorageMechanisms.interfaces.CacheStorage;
import com.nikhil.Cache.StorageMechanisms.interfaces.DBStorage;
import com.nikhil.Cache.WritePolicies.WritePolicy;

import java.util.concurrent.CompletableFuture;

public class WriteThroughPolicy<K, V> implements WritePolicy<K, V> {
    @Override
    public void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) {
        CompletableFuture<Void> cacheFuture = CompletableFuture.runAsync(() -> {
            try {
                cacheStorage.put(key, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Void> dbFuture = CompletableFuture.runAsync(() -> {
            try {
                dbStorage.write(key, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture.allOf(cacheFuture, dbFuture).join();
    }
}
