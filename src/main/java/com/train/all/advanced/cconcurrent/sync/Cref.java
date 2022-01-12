package com.train.all.advanced.cconcurrent.sync;

/**
 *  测试两个静态final和不加final的区别
 *  在字节码层面 A是常量
 * @author: kennys.chai
 * @date: 2022/1/11
 */
public class Cref {
    public static void main(String[] args) {
        int x = Counter.A;
        int y = Counter.B;
    }
}
