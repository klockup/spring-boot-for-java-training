package com.train.all.advanced.cconcurrent.base;

import java.util.concurrent.Callable;

/**
 *  一个简单的线程C，实现了Callable，可以做到有返回值
 * @author: kennys.chai
 * @date: 2022/1/12
 */
public class ThreadC implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        System.out.println("这是线程C");
        return "线程C";
    }



}
