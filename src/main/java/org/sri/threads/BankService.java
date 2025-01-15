package org.sri.threads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {

    private Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();

    public void transferMoney(int fromAccountId, int toAccountId, int amount) {
        // Synchronize only on the "from" and "to" accounts
        BankAccount fromAccount = accounts.get(fromAccountId);
        BankAccount toAccount = accounts.get(toAccountId);

        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                } else {
                    throw new RuntimeException("Insufficient balance!");
                }
            }
        }
    }
}
