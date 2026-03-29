import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeFinderDemo {

    /**
     * Shared utility method to check if a number is prime.
     */
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }

    /* * 1. EXTENDING THE THREAD CLASS
     * We create a custom thread by inheriting from java.lang.Thread.
     */
    static class PrimeThread extends Thread {
        private final int startRange;
        private final int endRange;

        public PrimeThread(String name, int startRange, int endRange) {
            super(name); // Set the thread name
            this.startRange = startRange;
            this.endRange = endRange;
        }

        @Override
        public void run() {
            List<Integer> primes = new ArrayList<>();
            for (int i = startRange; i <= endRange; i++) {
                if (isPrime(i)) primes.add(i);
            }
            System.out.printf("[%s] Primes found: %s%n", getName(), primes);
        }
    }

    /* * 2. IMPLEMENTING THE RUNNABLE INTERFACE
     * This is generally preferred over extending Thread because Java only allows 
     * single inheritance. Implementing Runnable leaves you free to extend another class.
     */
    static class PrimeRunnable implements Runnable {
        private final String taskName;
        private final int startRange;
        private final int endRange;

        public PrimeRunnable(String taskName, int startRange, int endRange) {
            this.taskName = taskName;
            this.startRange = startRange;
            this.endRange = endRange;
        }

        @Override
        public void run() {
            List<Integer> primes = new ArrayList<>();
            for (int i = startRange; i <= endRange; i++) {
                if (isPrime(i)) primes.add(i);
            }
            // Thread.currentThread().getName() gets the name of the thread executing this Runnable
            System.out.printf("[%s executing %s] Primes found: %s%n", 
                    Thread.currentThread().getName(), taskName, primes);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Using Thread Class ===");
        // Create and start threads directly
        PrimeThread thread1 = new PrimeThread("Thread-A", 1, 20);
        PrimeThread thread2 = new PrimeThread("Thread-B", 21, 40);
        thread1.start();
        thread2.start();

        // Wait for threads to finish before moving to the next section
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 2. Using Runnable Interface ===");
        // Create Runnables and pass them to Thread objects
        PrimeRunnable task1 = new PrimeRunnable("Task-1", 41, 60);
        PrimeRunnable task2 = new PrimeRunnable("Task-2", 61, 80);
        
        Thread runnableThread1 = new Thread(task1, "RunnableWorker-1");
        Thread runnableThread2 = new Thread(task2, "RunnableWorker-2");
        
        runnableThread1.start();
        runnableThread2.start();

        try {
            runnableThread1.join();
            runnableThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 3. Using Executor Framework ===");
        /*
         * The Executor Framework manages a pool of worker threads. 
         * Instead of manually creating and destroying threads (which is expensive 
         * for the CPU), we submit tasks to the pool, and the executor handles the rest.
         */
        int poolSize = 2;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        // Submit multiple tasks to the thread pool
        executor.submit(new PrimeRunnable("PoolTask-A", 81, 100));
        executor.submit(new PrimeRunnable("PoolTask-B", 101, 120));
        executor.submit(new PrimeRunnable("PoolTask-C", 121, 140));

        // Shutdown the executor to reject new tasks and gracefully finish existing ones
        executor.shutdown();
        
        try {
            // Block the main thread until all pool tasks are complete
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nAll tasks completed successfully.");
    }
}
