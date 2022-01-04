package com.train.all.advanced.ajvm.bytecode;

import lombok.extern.slf4j.Slf4j;

/**
 *  认识字节码
 *  一个简单包含四则运算和for if的程序
 * @author: kennys.chai
 * @date: 2021/12/29 
 */
@Slf4j
public class HelloByteCode {
    public static void main(String[] args) {
        int a=2;
        int b=4;
        int c=a*b;
        for (int i = 0; i < 10; i++) {
            c=c+i;
            if(3==i){
                c=c/a;
                c--;
            }

        }
        log.info(String.valueOf(c));
    }

}
