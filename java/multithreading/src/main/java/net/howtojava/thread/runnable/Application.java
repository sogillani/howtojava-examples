package net.howtojava.thread.runnable;

class Runner implements Runnable {
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
      Thread threadOne = new Thread(new Runner());
      threadOne.start();

      Thread threadTwo = new Thread(new Runner());
      threadTwo.start();
    }
}
