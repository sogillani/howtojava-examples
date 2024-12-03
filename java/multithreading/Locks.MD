
## Multiple Locks

### Introduction
In this section we will explore the use of multiple locks and synchronized code blocks to handle concurrent operations more efficiently.

In this section:
- Set up an application that processes data using multiple threads.
- Introduce issues with concurrent data access.
- Implement synchronized methods to ensure data consistency.
- Optimize performance using synchronized code blocks and multiple locks.

```java
public class Worker {
    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();
    private Random random = new Random();

    public synchronized void stageOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listOne.add(random.nextInt(100));
    }

    public synchronized void stageTwo() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listTwo.add(random.nextInt(100));
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }
    
    public void getListOne() {
        return listOne;
    }
    
    public void getListTwo() {
        return listTwo;
    }
}
```

Main Application with concurrent execution with threads
```
public class Application {
    public static void main(String[] args) {
        Worker worker = new Worker();

        Thread t1 = new Thread(() -> worker.process());
        Thread t2 = new Thread(() -> worker.process());

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start) + " ms");
        System.out.println("List1 size: " + worker.getListOne().size());
        System.out.println("List2 size: " + worker.getListTwo().size());
    }
}
```

#### Issues with Synchronized Methods
Using synchronized methods ensures that no two threads can access the same method simultaneously, but it can cause performance issues when methods do not need to be synchronized together.

#### Optimizing with Multiple Locks
By using multiple locks, we can allow concurrent access to different methods:
```
public class Worker {

    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();
    
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private Random random = new Random();

    public void stageOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }
}
```

### Conclusion
By using multiple locks and synchronized code blocks, we can improve the performance of our multithreaded applications while ensuring data consistency. This approach allows for more efficient use of resources and faster execution times.