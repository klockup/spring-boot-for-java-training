package com.train.all.roadtocoding.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程 Demo
 *
 * 线程有两种实现
 * 1 继承 Thread类
 * 2 实现 Runnable接口
 *
 * @author: cxy
 * @date: 2021/12/31
 */
@Slf4j
public class ThreadDemo {

    public static class MyThreadExThread extends Thread{
        @Override
        public void run() {
            log.info("this Thread extends Thread");
        }
    }

    public static class MyThreadImRunnable implements Runnable{

        @Override
        public void run() {
            log.info("this Thread implements Runnable");
        }
    }


    public static void main(String[] args)  {
//        Thread myThread1=new MyThreadExThread();
//        myThread1.start();
//        Thread myThread2= new Thread(new MyThreadImRunnable());
//        myThread2.start();
//        //此时可以看到 命令行中打出的是线程的提示，如果直接调用 run方法，则不是线程的调用方式。
//        myThread2.run();
//
//        //java8的函数式编程，还有一种简便的方式创建线程
//        new Thread(()->{
//            log.info("函数式编程，不需要事先创建声明线程类");
//        }).start();
        Thread myThread2= new Thread(new MyThreadImRunnable());
        myThread2.start();

        try {
            myThread2.wait();
        } catch (InterruptedException e) {
            log.error("22222");
            e.printStackTrace();

        }
    }

}
