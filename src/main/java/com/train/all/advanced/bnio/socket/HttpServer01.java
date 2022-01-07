package com.train.all.advanced.bnio.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个最简单的HTTP服务器
 * 1 创建一个ServerSocket
 * 2 绑定8001
 * 3 当有客户端请求时可以通过accept的方法拿到socket，进而处理
 * 4 sleep 20ms，模拟业务操作
 * 5 模拟输出 Http报文头和hello
 * 6 关闭socket
 *
 * 启动后使用localhost:8001 访问，使用压测工具测一下吞吐量
 *
 * 本机测试结果
 * status-200：866
 * RPS：25
 * 慢的原因：
 * 1.sleep 20ms
 * 2.BIO 阻塞请求
 * 3.本质上是单线程
 *
 * @author kennyS
 */
@Slf4j
public class HttpServer01 {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(8001);
            while (true){
                Socket socket=serverSocket.accept();
                service(socket);
            }
        } catch (IOException e) {
            log.error("创建socket异常");
            e.printStackTrace();
        }

    }

    private static void service(Socket socket){
        try {
            Thread.sleep(20);
            PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello,nio");
            printWriter.close();
            socket.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

}
