/**
 * Created by VictorZelenin on 30.03.2016.
 */
public class Main {

    private static final int firstQueueCapacity = 3;


    public static void main(String[] args) {

        new CPUProcess(10, 5000, 1000, firstQueueCapacity).start();

    }

}
