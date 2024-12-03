package net.howtojava.thread.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();

    private Random random = new Random();
    private Object lockOne = new Object();
    private Object lockTwo = new Object();

    public void processOne() {
        synchronized(lockOne) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            listOne.add(random.nextInt());
        }
    }

    public void processTwo() {
        synchronized (lockTwo) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            listTwo.add(random.nextInt());
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            processOne();
            processTwo();
        }
    }

    public void execute() {
        long start = System.currentTimeMillis();

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        threadOne.start();

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("List One Size: " + listOne.size());
        System.out.println("List Two Size: " + listTwo.size());
    }

    public static void main(String[] args) {
        new Worker().execute();
    }
}
