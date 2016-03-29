import java.util.LinkedList;

/**
 * Created by VictorZelenin on 30.03.2016.
 */
public class CPUQueue {

    private java.util.Queue<Process> processQueue;

    private int capacity;

    public CPUQueue(int capacity) {

        this.capacity = capacity;

        processQueue = new LinkedList<>();
    }


    public synchronized void add(Process process) {

        if (processQueue.size() >= capacity) {
            System.out.println(process + " destroyed!!");

        } else {

            synchronized (this) {

                System.out.println(process + "  added to queue2");

                processQueue.offer(process);

            }

        }
    }

    public synchronized Process poll() {

        if (processQueue.size() <= 0) {
            throw new RuntimeException();

        }


        Process polledProcess;
        synchronized (this) {

            polledProcess = processQueue.poll();

            System.out.println(this + " getQueueSize: " + this.getQueueSize());

            System.out.println(polledProcess + " polled from queue2");

        }


        return polledProcess;
    }

    public synchronized int getQueueSize() {

        return processQueue.size();

    }

    @Override
    public String toString() {
        return "CPUQueue-2";
    }
}
