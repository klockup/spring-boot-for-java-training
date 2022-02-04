package com.train.all.advanced.cconcurrent.sync;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Counter {
    public final static int A=10;
    public static int B=20;
    
    private int sum = 0;

    private static Object object=new Object();

    public  void incr() {
        synchronized(new Object()) {
            sum = sum + 1;
        }
    }

    public void add(){
        for (int i = 0; i < 10; i++) {
            log.info("{}-{}",Thread.currentThread().getName(),String.valueOf(i));
        }
    }

    public int getSum() {
        return sum;
    }
    
    public static void main(String[] args) throws InterruptedException {
        int loop = 100000;
        
        // test single thread
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incr();
        }

        System.out.println("single thread: " + counter.getSum());
    
        // test multiple threads
        final Counter counter2 = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
            counter2.add();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
            counter2.add();
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);

        System.out.println("multiple threads: " + counter2.getSum());


    }
}
