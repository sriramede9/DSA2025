package org.sri.threads.executorservice;

import java.util.concurrent.*;
import java.util.*;

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<Integer>> futures = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            futures.add(executor.submit(() -> {
                Thread.sleep(1000);
                return taskId * 2;
            }));
        }
        
        for (Future<Integer> future : futures) {
            try {
                System.out.println("Result: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        
        executor.shutdown();
    }
}
