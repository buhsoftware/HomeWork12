package FizzBuzz;

import java.util.StringJoiner;

public class FizzBuzz {

    private int counter = 1;
    private int maxValue;

    private final StringJoiner stringJoiner = new StringJoiner(", ");

    public FizzBuzz(int maxValue) {
        this.maxValue = maxValue;
    }

    public void run() {

        Thread thread1 = new Thread(() -> {
            try {
                fizzbuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                fizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                buzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                number();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(stringJoiner);
    }

    synchronized void fizz() throws InterruptedException {
        while (counter <= maxValue) {
            if ((counter % 3 == 0) && !(counter % 5 == 0)) {
                stringJoiner.add("fizz");
                counter++;
                notifyAll();
            } else
                wait();
        }

    }

    synchronized void fizzbuzz() throws InterruptedException {
        while (counter <= maxValue) {
            if ((counter % 3 == 0) && (counter % 5 == 0)) {
                stringJoiner.add("fizzbuzz");
                counter++;
                notifyAll();
            } else
                wait();
        }

    }

    synchronized void number() throws InterruptedException {
        while (counter <= maxValue) {
            if (!((counter % 5 == 0) || (counter % 3 == 0))) {
                stringJoiner.add(String.valueOf(counter));
                counter++;
                notifyAll();
            } else
                wait();
        }
    }

    synchronized void buzz() throws InterruptedException {
        while (counter <= maxValue) {
            if ((counter % 5 == 0) && !(counter % 3 == 0)) {
                stringJoiner.add("buzz");
                counter++;
                notifyAll();
            } else
                wait();
        }
    }
}
