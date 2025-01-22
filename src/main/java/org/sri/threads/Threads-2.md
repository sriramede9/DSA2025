# **Java CompletableFuture and Backpressure**

This document summarizes key concepts, use cases, and examples of using `CompletableFuture` in Java, focusing on handling backpressure and comparing it with Reactor programming.

---

## **1. What is Backpressure?**

Backpressure occurs when the **producer** generates tasks faster than the **consumer** can process them. This leads to overwhelming the consumer and potential resource exhaustion.

### **Analogy**
- Imagine a lemonade stand:
    - **Normal flow**: Customers come one by one, and orders are fulfilled smoothly.
    - **Overload (backpressure)**: Too many customers arrive at once, overwhelming the system.

---

## **2. Handling Backpressure in CompletableFuture**

`CompletableFuture` doesn’t have built-in backpressure handling, but we can manage it using:

### **Techniques**
1. **Throttling**: Control the rate of task submission.
2. **Batches**: Process tasks in chunks.
3. **Queue Management**: Use a blocking queue to regulate task flow.

### **Examples**

#### **1. Throttling**
Throttle task submissions by introducing delays.

```java
ExecutorService executor = Executors.newFixedThreadPool(3);
for (int i = 1; i <= 10; i++) {
    int taskId = i;
    CompletableFuture.runAsync(() -> {
        System.out.println("Processing task " + taskId);
    }, executor);
    Thread.sleep(300); // Pause between submissions
}
executor.shutdown();
```

#### **2. Batching**
Group tasks into smaller chunks to process them efficiently.

```java
List<CompletableFuture<Void>> futures = new ArrayList<>();
for (int i = 1; i <= 10; i++) {
    if (i % 3 == 0) {
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        futures.clear();
    }
    futures.add(CompletableFuture.runAsync(() -> System.out.println("Task: " + i)));
}
```

#### **3. Queue Management**
Use a `BlockingQueue` to limit the number of tasks in progress.

```java
BlockingQueue<Integer> taskQueue = new LinkedBlockingQueue<>(5);
CompletableFuture.runAsync(() -> {
    for (int i = 1; i <= 20; i++) {
        taskQueue.put(i);
        System.out.println("Added task " + i);
    }
});

CompletableFuture.runAsync(() -> {
    while (true) {
        Integer task = taskQueue.take();
        System.out.println("Processing task " + task);
        Thread.sleep(1000);
    }
});
```

---

## **3. Comparing CompletableFuture and Reactor**

| Feature                      | CompletableFuture                     | Reactor (Flux)                  |
|------------------------------|---------------------------------------|---------------------------------|
| **Backpressure Support**     | Manual (e.g., throttling, batching)  | Built-in                        |
| **Concurrency Model**        | Relies on `ExecutorService`          | Efficient thread reuse          |
| **Stream Processing**        | Limited                              | Designed for streams            |
| **Ease of Use**              | Simple for single tasks              | Better for reactive pipelines   |


### **Reactor Example: Handling Backpressure**

```java
Flux.range(1, 100)
    .onBackpressureBuffer(10) // Buffer tasks
    .publishOn(Schedulers.parallel(), 2) // Process 2 tasks at a time
    .subscribe(task -> {
        System.out.println("Processing task " + task);
        Thread.sleep(1000);
    });
```

---

## **4. Summary**

- Use `CompletableFuture` for task-based concurrency with simple logic.
- Handle backpressure with throttling, batching, or queueing.
- Use Reactor for data streams and built-in backpressure handling.

---
