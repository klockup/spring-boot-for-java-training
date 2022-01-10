package com.train.all.advanced.bnio.client;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class OkClientTest {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://www.baidu.com/s?wd=java")
                .get()
                .build();
        try {
            Response response=client.newCall(request).execute();
            log.info(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
