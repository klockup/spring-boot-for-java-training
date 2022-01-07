package com.train.all.advanced.bnio.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在01的基础上变成多线程处理请求
 *
 * 再次测试下吞吐量
 * 本次测试结果
 * status-200：35592
 * status-303：680
 * RPS:1167
 *
 * @author kennyS
 */
@Slf4j
public class HttpServer02 {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(8002);
            while (true){
                final Socket socket=serverSocket.accept();
                new Thread(()->{
                    service(socket);
                }).start();
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
