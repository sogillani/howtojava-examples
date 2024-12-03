package net.howtojava.thread.cache;


import java.util.Scanner;

class Processor extends Thread {
    private volatile boolean running = true;
    @Override
    public void run() {
        while (running) {
            System.out.println("Tick!");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class Driver {

    public static void main(String[] args) {
        Processor processorOne = new Processor();
        processorOne.start();

        System.out.println("Press return to stop ... ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        processorOne.shutdown();
    }
}
