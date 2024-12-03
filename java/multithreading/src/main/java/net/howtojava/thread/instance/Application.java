package net.howtojava.thread.instance;

class Runner extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Count: " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Application {
    public static void main(String[] args) {
        Runner runnerOne = new Runner();
        runnerOne.start();

        Runner runnerTwo = new Runner();
        runnerTwo.start();
    }
}