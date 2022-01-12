package com.train.all.advanced.cconcurrent;

/**
 *  一个简单的线程A
 * @author: kennys.chai
 * @date: 2022/1/12
 */
public class ThreadA extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是线程A");
    }
}
