
import static java.lang.Thread.sleep;

public class Task1test {

    public static void main(String[] args) {

        new Thread(() -> {
            int startTime = 0;
            while (true) {
                try {
                    sleep(1000);
                    startTime++;
                    System.out.println("секунд = " + startTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ).start();

        new Thread(() -> {
            while (true) {
                try {
                    sleep(5000);
                    System.out.println("Минуло 5 секунд");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ).start();

    }
}
