package net.howtojava.thread.synch;

public class Application {

    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Application application = new Application();
        application.doWork();

    }

    public synchronized void increment() {
        count++;
    }

    public void doWork() throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    increment();
                }
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    increment();
                }
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("Count is: " + count);
    }
}
