/**
 * Created by VictorZelenin on 30.03.2016.
 */
public class CPUProcess extends Thread {

    private static int ID = 0;
    private int id;

    private CPUQueue queue;
    private CPU CPU;

    private int numberOfThreads;
    private long maxProcessingTime, maxWaitingTime;


    public CPUProcess(int numberOfThreads, long maxProcessingTime, long maxWaitingTime, int capacity) {

        id = ++ID;

        this.numberOfThreads = numberOfThreads;
        this.maxWaitingTime = maxWaitingTime;
        this.maxProcessingTime = maxProcessingTime;

        queue = new CPUQueue(capacity);
        CPU = new CPU(queue);

        CPU.start();


    }


    @Override
    public void run() {

        for (int i = 0; i < numberOfThreads; i++) {

            Process process = new Process((long) (Math.random() * maxProcessingTime));

            System.out.println("Process-" + process.getId() + " has been generated" + " in CPUProcess-" + id);

            if (!CPU.isBusy()) {
                CPU.loadCpuProcess(process);
            } else {

                queue.add(process);
            }


            try {
                sleep((long) (Math.random() * maxWaitingTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(queue + "  getQueueSize: " + queue.getQueueSize());

        }


    }

}
