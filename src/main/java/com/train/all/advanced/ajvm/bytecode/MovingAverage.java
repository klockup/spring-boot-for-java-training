package com.train.all.advanced.ajvm.bytecode;

/**
 *  认识字节码，并且查看字节码文件理解
 * @author: kennys.chai
 * @date: 2021/12/29
 */
public class MovingAverage {

    private int count=0;
    private double sum=0.0D;

    public void submit(double value){
        this.count++;
        this.sum+=value;
    }

    public double getAvg(){
        if(0==this.count){
            return sum;
        }
        return this.sum/this.count;
    }

    public static void main(String[] args) {
        MovingAverage ma=new MovingAverage();
        int num1=1;
        int num2=2;
        ma.submit(num1);
        ma.submit(num2);
        double avg=ma.getAvg();

    }

}
