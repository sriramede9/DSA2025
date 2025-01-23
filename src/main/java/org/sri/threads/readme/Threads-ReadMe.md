# Java Multithreading Revision Notes

## 1. **Why should money transfer operations be synchronous?**
Money transfer operations involve updating two accounts (deduction and deposit), and these updates must be atomic to ensure consistency. Without synchronization, concurrent transactions could lead to:

- Overdrawing accounts
- Incorrect balances due to race conditions

**Solution:** Use `@Transactional` for database operations or synchronize methods when using in-memory data.

---

## 2. **Can using a `ConcurrentHashMap` avoid synchronization?**
No, `ConcurrentHashMap` ensures thread-safe access to individual operations, but complex operations like "check and modify" (e.g., checking balance before transferring money) still require explicit synchronization.

**Example:**
```java
synchronized (this) {
    if (balance >= amount) {
        balance -= amount;
    }
}
```

---

## 3. **Nested Synchronization: Why synchronize on `from` and then `to` accounts?**
To avoid deadlocks during nested synchronization:
- Always acquire locks in a consistent order.
- Example: Lock the `fromAccount` first, then the `toAccount`.

```java
synchronized (from) {
    synchronized (to) {
        // Transfer logic
    }
}
```

---

## 4. **Atomicity and why it matters**
Atomic operations ensure that a sequence of operations executes as a single, indivisible step.

**Example Issue:**
- `check balance -> deduct -> update` is not atomic.
- A thread could interrupt between these steps, leading to race conditions.

---

## 5. **Why doesn't `AtomicInteger` solve all race conditions?**
`AtomicInteger` ensures atomic increment/decrement but doesn’t help with compound operations like:

```java
if (balance.get() >= amount) {
    balance.addAndGet(-amount);
}
```
Race conditions occur because `if` and `addAndGet` are not a single atomic operation.

**Solution:** Use `compareAndSet` for atomic compound updates:
```java
while (true) {
    int current = balance.get();
    if (current >= amount) {
        if (balance.compareAndSet(current, current - amount)) break;
    }
}
```

---

## 6. **How to identify and solve race conditions?**
### Example:
**Problem:** Multiple users booking tickets concurrently.

**Race Condition:**
- Two users check available tickets simultaneously (e.g., 50 tickets).
- Both proceed to book, resulting in overbooking.

**Solutions:**
1. **Synchronized Method:**
   ```java
   public synchronized void bookTicket(int tickets) {
       if (availableTickets >= tickets) {
           availableTickets -= tickets;
       }
   }
   ```

2. **ReentrantLock:**
   ```java
   private final Lock lock = new ReentrantLock();
   public void bookTicket(int tickets) {
       lock.lock();
       try {
           if (availableTickets >= tickets) {
               availableTickets -= tickets;
           }
       } finally {
           lock.unlock();
       }
   }
   ```

---

## 7. **Difference between `synchronized` and `ReentrantLock`**
| Aspect             | `synchronized`                  | `ReentrantLock`               |
|--------------------|----------------------------------|--------------------------------|
| Simplicity         | Easy to use                     | More complex, requires manual locking/unlocking |
| Features           | Basic lock                      | Try-lock, timed lock, fairness policies         |
| Performance        | Better for uncontended locks    | Better for high contention                     |

---

## 8. **Admin and User Race Condition in Ticket System**
If an admin increases tickets while users are booking:

**Problem:** Users might see outdated ticket counts.

**Solution:**
Synchronize both `bookTicket` and `increaseTickets` methods:

```java
public synchronized void bookTicket(int tickets) {
    if (availableTickets >= tickets) {
        availableTickets -= tickets;
    }
}

public synchronized void increaseTickets(int tickets) {
    availableTickets += tickets;
}
```

---

## 9. **How does `lock.tryLock()` differ from `synchronized(this)`?**
- `lock.tryLock()` allows a thread to attempt acquiring a lock without blocking indefinitely.
- `synchronized(this)` blocks the thread until the lock becomes available.

**Example with `tryLock`:**
```java
if (lock.tryLock()) {
    try {
        // Critical section
    } finally {
        lock.unlock();
    }
}
```

---

## 10. **Why synchronize deposit operations?**
Even for simple operations like deposits, synchronization is needed to:
1. Prevent race conditions.
2. Ensure atomic updates when multiple threads access the same resource.

---

## Summary of Key Concepts
1. **Synchronization:** Ensures mutual exclusion for shared resources.
2. **ReentrantLock:** Provides more advanced locking mechanisms.
3. **Atomicity:** Compound operations require additional synchronization.
4. **Deadlocks:** Avoid by acquiring locks in a consistent order.
5. **Race Conditions:** Occur when threads access shared resources without proper synchronization.

---

### Frequently Used Patterns
1. **Double-Checked Locking:**
   ```java
   if (instance == null) {
       synchronized (this) {
           if (instance == null) {
               instance = new Singleton();
           }
       }
   }
   ```

2. **Producer-Consumer:**
   Using `wait` and `notify`:
   ```java
   synchronized (this) {
       while (queue.isEmpty()) {
           wait();
       }
       queue.remove();
       notifyAll();
   }
   ```

---

### Additional Notes
- Always use `finally` to release locks.
- Prefer `ConcurrentHashMap` or `AtomicInteger` for thread-safe single operations.
- Use `compareAndSet` for atomic compound operations.
