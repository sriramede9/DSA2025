package org.sri.threads.ticket;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketBookingSystem {
    private int availableTickets = 50;

    public synchronized void bookTicket(String user, int ticketsToBook) {
        if (availableTickets >= ticketsToBook) {
            System.out.println(user + " is trying to book " + ticketsToBook + " tickets...");
            availableTickets -= ticketsToBook; // Deduct tickets
            System.out.println(user + " successfully booked " + ticketsToBook + " tickets.");
        } else {
            System.out.println(user + " failed to book " + ticketsToBook + " tickets. Not enough tickets available.");
        }
    }

    public  void bookTicket2(String user, int ticketsToBook) {
        synchronized (this){
            if (availableTickets >= ticketsToBook) {
                System.out.println(user + " is trying to book " + ticketsToBook + " tickets...");
                availableTickets -= ticketsToBook; // Deduct tickets
                System.out.println(user + " successfully booked " + ticketsToBook + " tickets.");
            } else {
                System.out.println(user + " failed to book " + ticketsToBook + " tickets. Not enough tickets available.");
            }
        }
    }

    private final Lock lock = new ReentrantLock();

    public void bookTicket3(String user, int ticketsToBook) {
        boolean lockAcquired=false;
        try {
            lockAcquired=lock.tryLock(500, TimeUnit.MILLISECONDS);
            if (lockAcquired) { // Try to acquire lock
                if (availableTickets >= ticketsToBook) {
                    System.out.println(user + " is trying to book " + ticketsToBook + " tickets...");
                    availableTickets -= ticketsToBook;
                    System.out.println(user + " successfully booked " + ticketsToBook + " tickets.");
                } else {
                    System.out.println(user + " failed to book " + ticketsToBook + " tickets. Not enough tickets available.");
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (lockAcquired) {
                lock.unlock(); // Unlock only if the lock was acquired
            }
        }
    }

    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();

        // Simulate multiple users trying to book tickets
        Runnable user1 = () -> bookingSystem.bookTicket3("User1", 30);
        Runnable user2 = () -> bookingSystem.bookTicket3("User2", 25);
        Runnable user3 = () -> bookingSystem.bookTicket3("User3", 10);

        // Start threads
        Thread t1 = new Thread(user1);
        Thread t2 = new Thread(user2);
        Thread t3 = new Thread(user3);

        t1.start();
        t2.start();
        t3.start();
    }
}
