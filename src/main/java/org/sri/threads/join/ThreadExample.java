package org.sri.threads.join;

public class ThreadExample {
    public static void main(String[] args) {
        Thread quickThread = new Thread(() -> {
            System.out.println("Quick thread starting");
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Quick thread finished");
        });

        Thread longThread = new Thread(() -> {
            System.out.println("Long thread starting");
            try {
                Thread.sleep(60000); // Sleep for 1 minute
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Long thread finished");
        });

        // Set this to true for daemon thread, false for non-daemon
        // if deamon thread, it automatically exits without waiting for longThread
        boolean isDaemon = false;
        longThread.setDaemon(isDaemon);

        quickThread.start();
        longThread.start();

        System.out.println("Main thread exiting");
    }
}
