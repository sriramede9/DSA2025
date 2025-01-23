# Deadlock Prevention in Banking Systems

Deadlocks can occur in systems where multiple threads attempt to acquire locks on shared resources in different orders. In the context of a banking system, deadlocks often arise during fund transfers between accounts. This README explains how to prevent deadlocks using a consistent lock acquisition strategy.

---

## Problem Scenario

**Example:**
1. User A tries to transfer $20 to User B.
2. User B tries to transfer $10 back to User A.

### Thread Execution:
- Thread 1 locks Account A and waits for Account B.
- Thread 2 locks Account B and waits for Account A.

This creates a circular wait, resulting in a deadlock.

---

## Solution: Consistent Lock Order

To prevent deadlocks, always acquire locks in a deterministic order based on a unique identifier like the **account number**.

### Rules:
1. Always lock the account with the smaller number first.
2. Lock the account with the larger number second.

This ensures that no matter how many threads are involved, all of them acquire locks in the same order, breaking the cycle that leads to deadlocks.

---

## Implementation Strategy

### Pseudo Code:
```java
public void transfer(Account from, Account to, double amount) {
    Account firstLock = from.getId() < to.getId() ? from : to;
    Account secondLock = from.getId() < to.getId() ? to : from;

    synchronized (firstLock) {
        synchronized (secondLock) {
            if (from.getBalance() >= amount) {
                from.debit(amount);
                to.credit(amount);
            }
        }
    }
}
```

### Explanation:
1. Determine the lock order using the account IDs.
2. Acquire the lock for the account with the smaller ID first.
3. Then acquire the lock for the account with the larger ID.
4. Perform the transfer operation atomically within the nested synchronized blocks.

---

## Advantages

### 1. **Deterministic Locking Order**
- Ensures all threads follow the same lock acquisition sequence, preventing circular waits.

### 2. **Scalability**
- Can handle millions of accounts since the logic only depends on unique IDs.

### 3. **Simplicity**
- Easy to understand and implement without requiring advanced deadlock detection mechanisms.

---

## Fun Analogy
Imagine two friends want to cross a narrow bridge:
- Friend A always gives way if their name comes alphabetically before Friend B's.
- This consistent rule ensures no head-on collisions (deadlocks) occur.

In the banking system, **account numbers are like names**, and the bridge is the **shared resource being locked**.

---

## Key Takeaways
1. Deadlocks arise when threads acquire locks in different orders.
2. Use a consistent lock acquisition order to prevent circular waits.
3. Account numbers (or unique identifiers) are critical for determining lock order.