package com.nikhil.Cache.Executers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class KeyBasedExecutor {
    private final ExecutorService[] executorServices;
    private final int numExecuters;


    public KeyBasedExecutor(int numExecuters) {
        this.numExecuters = numExecuters;
        this.executorServices = new ExecutorService[numExecuters];
        for (int i = 0; i < executorServices.length; i++) {
            executorServices[i] = Executors.newSingleThreadExecutor();
        }
    }

    public <T> CompletableFuture<T> submitTask(Object key, Supplier<T> task) {
        int index = getExecuterIndexForKey(key);
        ExecutorService executorService = executorServices[index];
        return CompletableFuture.supplyAsync(task, executorService);
    }

    public int getExecuterIndexForKey(Object key) {
        return Math.abs(key.hashCode() % numExecuters);
    }

    public void shutdown() {
        for (ExecutorService executorService : executorServices) {
            executorService.shutdown();
        }
    }

}
