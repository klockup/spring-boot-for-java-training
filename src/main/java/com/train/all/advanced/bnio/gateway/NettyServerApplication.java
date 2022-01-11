package com.train.all.advanced.bnio.gateway;

import com.train.all.advanced.bnio.gateway.inbound.HttpInBoundServer;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 *  一个简单的网关
 * @author: kennys.chai
 * @date: 2022/1/10
 */
@Slf4j
public class NettyServerApplication {

    private static final String GATEWAY_NAME="nio-gateway";
    private static final String GATEWAY_VERSION="1.0.0";

    public static void main(String[] args) {

        String proxyPort=System.getProperty("proxyPort","8888");
        String proxyServer=System.getProperty("proxyServer","localhost:8088");
        //  http://localhost:8888/api/hello  ==> gateway API
        //  http://localhost:8088/api/hello  ==> backend service
        // java -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar  #作为后端服务

        int port = Integer.parseInt(proxyPort);

        log.info("{} {}{}",GATEWAY_NAME , GATEWAY_VERSION ," starting...");
        HttpInBoundServer server=new HttpInBoundServer(port, Arrays.asList(proxyServer));
        log.info("{} {} {}{}{}",GATEWAY_NAME,GATEWAY_VERSION,"started at http://localhost:",proxyPort,"for server:");

        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
