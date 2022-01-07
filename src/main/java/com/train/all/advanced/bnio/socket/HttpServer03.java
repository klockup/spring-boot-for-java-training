package com.train.all.advanced.bnio.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 在02的基础上变成线程池的多线程处理请求
 *
 * 再次测试下吞吐量
 * 本次测试结果
 * status-200：36480
 * status-303：297
 * RPS:1183.9
 *
 * 02中的相当于每次请求都需要用建一个单独的线程。使用线程池后对系统的开销方面大大降低
 *
 * @author kennyS
 */
@Slf4j
public class HttpServer03 {

    public static void main(String[] args) {
        try {
//            ExecutorService executorService= new ThreadPoolExecutor(2,40,2, TimeUnit.SECONDS,new ArrayBlockingQueue<>(20));
            ExecutorService executorService=Executors.newFixedThreadPool(40);
            final ServerSocket serverSocket=new ServerSocket(8003);
            while (true){
                final Socket socket=serverSocket.accept();
                executorService.execute(()->{
                    service(socket);
                });
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
