package org.sri.threads;

public class BankAccount {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {
        this.balance-=amount;
    }

    public void deposit(int amount) {
        this.balance+=amount;
    }
}
