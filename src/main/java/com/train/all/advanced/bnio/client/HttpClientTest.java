package com.train.all.advanced.bnio.client;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

@Slf4j
public class HttpClientTest {

    public static void main(String[] args) {
     CloseableHttpClient httpClient = HttpClientBuilder.create().build();
     HttpGet httpGet = new HttpGet("http://www.baidu.com/s?wd=java");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            log.info(JSON.toJSONString(response.getStatusLine().getStatusCode()));
            log.info(JSON.toJSONString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
