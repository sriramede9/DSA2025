package org.sri.threads.executorservice;

import java.util.concurrent.*;

public class BasicExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            Runnable runnable = () -> {
                System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executor.submit(runnable);
        }
        
        executor.shutdown(); // closed for taking any new tasks
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) { // giving main thread 5 seconds and if fails calling shutdown immediately
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
