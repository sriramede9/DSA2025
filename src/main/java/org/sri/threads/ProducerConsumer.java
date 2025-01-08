package org.sri.threads;

import java.util.LinkedList;
import java.util.Queue;

class SharedQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity = 5;

    // Producer method
    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Queue is full. Producer is waiting...");
            wait(); // Wait until space is available
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notify(); // Notify waiting consumers
    }

    // Consumer method
    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty. Consumer is waiting...");
            wait(); // Wait until something is produced
        }
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notify(); // Notify waiting producers
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedQueue.produce(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sharedQueue.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
