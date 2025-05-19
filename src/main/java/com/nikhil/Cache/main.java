package com.nikhil.Cache;

import com.nikhil.Cache.EvictionAlgorithms.ConcreteEvictionAlgorithms.LRUEvictionAlgorithm;
import com.nikhil.Cache.EvictionAlgorithms.EvictionAlgorithm;
import com.nikhil.Cache.MainCache.Cache;
import com.nikhil.Cache.StorageMechanisms.ConcreteStorages.InMemoryCacheStorage;
import com.nikhil.Cache.StorageMechanisms.ConcreteStorages.SimpleDbStorage;
import com.nikhil.Cache.StorageMechanisms.interfaces.CacheStorage;
import com.nikhil.Cache.StorageMechanisms.interfaces.DBStorage;
import com.nikhil.Cache.WritePolicies.ConcreteWritePolicy.WriteThroughPolicy;
import com.nikhil.Cache.WritePolicies.WritePolicy;

public class main {
    public static void main(String[] args) {
        try {
            // Set a small capacity for the in-memory cache (e.g., 5 items)
            CacheStorage<String, String> cacheStorage = new InMemoryCacheStorage<>(5);
            // The underlying persistent store (DB storage) can be assumed to have large or unlimited capacity.
            DBStorage<String, String> dbStorage = new SimpleDbStorage<>();
            // Create the write-through policy (writes concurrently to both storages).
            WritePolicy<String, String> writePolicy = new WriteThroughPolicy<>();
            // Create the LRU eviction algorithm.
            EvictionAlgorithm<String> evictionAlg = new LRUEvictionAlgorithm<>();
            // Create the cache with 4 executor threads to guarantee per-key ordering.
            Cache<String, String> cache = new Cache<>(cacheStorage, dbStorage, writePolicy, evictionAlg, 4);

            // Demonstrate write operations.
            cache.updateData("A", "Apple").join();
            cache.updateData("B", "Banana").join();
            cache.updateData("C", "Cherry").join();
            cache.updateData("D", "Durian").join();
            cache.updateData("E", "Elderberry").join();

            // At this point, the in-memory cache is at capacity.
            // The next write will trigger eviction (of the least recently used key) from the cache.
            cache.updateData("F", "Fig").join();

            // Demonstrate read operations.
            try {
                String valueA = cache.accessData("A").join();
                System.out.println("A: " + valueA);
            } catch(Exception e) {
                System.out.println("A is evicted or not found in cache.");
            }

            String valueF = cache.accessData("F").join();
            System.out.println("F: " + valueF);

            // Update an existing key and then read it to demonstrate read-your-own-writes.
            cache.updateData("B", "Blueberry").join();
            String valueB = cache.accessData("B").join();
            System.out.println("B: " + valueB);

            // Shut down executors when finished.
            cache.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
