/**
 * Created by VictorZelenin on 30.03.2016.
 */
public class Process {

    private int id;
    private static int nextId = 0;
    private long time; // час на опрацювання даного процесу CPU

    public Process(long time) {

        id = ++nextId;
        this.time = time;

    }


    public int getId() {
        return id;
    }


    // simulate working
    public void executeProcess() {
        try {

            Thread.sleep(time);  // processing time

        } catch (InterruptedException e) {
            System.err.println("Process-" + id + " was interrupted, check your's logs");
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {

        return "Process-" + id;

    }
}
