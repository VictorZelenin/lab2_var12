/**
 * Created by VictorZelenin on 30.03.2016.
 */
public class CPU extends Thread {

    private Process cpuProcess; // current process
    private volatile boolean isBusy = false;
    private static int ID = 0;
    private int cpuID;

    private CPUQueue queue;


    public CPU(CPUQueue queue) {

        cpuID = ++ID;

        this.queue = queue;

    }


    public boolean isBusy() {
        return isBusy;
    }


    /**
     * @param process -- current process
     * @throws RuntimeException
     */
    public void loadCpuProcess(Process process) {


        if (process == null) {
            throw new RuntimeException("Serious Exception!!!");
        }

        cpuProcess = process;

        synchronized (this) {

            System.out.println(this + " notified");
            notify(); // повідомляємо, що прийшов новий процес

        }


    }


    @Override
    public void run() {

//        System.out.println("i'm here");
        while (!isInterrupted()) {


            if (cpuProcess != null) {

                executeProcess(cpuProcess);

                cpuProcess = null;


            } else if (queue.getQueueSize() != 0) {

                executeProcess(queue.poll());

            } else {

                synchronized (this) { // синхронізуємо по CPU, чекаємо  поки не отримаємо новий процес

                    try {

                        System.out.println(this + " is waiting for");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }


        }


    }

    /**
     * @param process -- виконуваний процес
     *                <p>
     *                опрацьвує поточний процес
     */
    private void executeProcess(Process process) {


        isBusy = true;

        System.out.println(this + " has started " + process);

        process.executeProcess();

        System.out.println(this + " has finished " + process);

        isBusy = false;


    }


    @Override
    public String toString() {
        return "CPU-" + cpuID;
    }


    // testing unit
    public static void main(String... args) {


    }
}
