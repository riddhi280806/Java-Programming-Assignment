import java.util.LinkedList;

// 1. The Shared Resource (Buffer)
class SharedBuffer {
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;

    /**
     * Called by the Producer thread to add items to the buffer.
     */
    public synchronized void produce(int value) throws InterruptedException {
        // We use a while loop to check the condition to avoid "spurious wakeups"
        while (buffer.size() == CAPACITY) {
            System.out.println("Buffer is FULL. Producer is waiting...");
            // Thread gives up the lock and goes to sleep until notified
            wait(); 
        }

        buffer.add(value);
        System.out.println("Produced: " + value + " | Buffer size: " + buffer.size());
        
        // Notify the Consumer that there is now data to consume
        notifyAll(); 
    }

    /**
     * Called by the Consumer thread to remove items from the buffer.
     */
    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer is EMPTY. Consumer is waiting...");
            // Thread gives up the lock and goes to sleep until notified
            wait(); 
        }

        int consumedValue = buffer.removeFirst();
        System.out.println("Consumed: " + consumedValue + " | Buffer size: " + buffer.size());
        
        // Notify the Producer that there is now space in the buffer
        notifyAll(); 
        
        return consumedValue;
    }
}

// 2. The Producer Thread
class Producer implements Runnable {
    private final SharedBuffer sharedBuffer;

    public Producer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        int value = 1;
        try {
            while (true) {
                sharedBuffer.produce(value++);
                // Simulate time taken to produce an item
                Thread.sleep(500); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted.");
        }
    }
}

// 3. The Consumer Thread
class Consumer implements Runnable {
    private final SharedBuffer sharedBuffer;

    public Consumer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                sharedBuffer.consume();
                // Simulate time taken to consume/process an item (slower than producer to force a full buffer)
                Thread.sleep(1000); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted.");
        }
    }
}

// 4. Main Class to run the simulation
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        System.out.println("=== Starting Producer-Consumer Simulation ===\n");
        
        SharedBuffer sharedBuffer = new SharedBuffer();

        // Create the threads, sharing the exact same buffer instance
        Thread producerThread = new Thread(new Producer(sharedBuffer), "Producer");
        Thread consumerThread = new Thread(new Consumer(sharedBuffer), "Consumer");

        producerThread.start();
        consumerThread.start();
        
        // Note: This program runs infinitely. You will need to stop it manually.
    }
}
