package com.train.all.commoon.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static org.apache.commons.codec.Charsets.UTF_8;

/**
 * HttpUtil
 *
 */
@Slf4j
public class HttpUtil {

    private HttpUtil() {
    }

    /**
     * kennys.chai : 2020/09/24 接口新增超时参数 millisecond,正常传空即可
     */
    public static String httpPost(String url, String jsonParam, Map<String, String> header,String millisecond) {
        //post请求返回结果
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String message = "";
        HttpPost method = new HttpPost(url);
        if (null != jsonParam) {
            StringEntity entity = new StringEntity(jsonParam, UTF_8);
            entity.setContentEncoding(UTF_8.toString());
            entity.setContentType(APPLICATION_JSON.toString());
            method.setEntity(entity);
        }
        if(!StringUtils.isEmpty(millisecond)){
            Integer ms=Integer.valueOf(millisecond);
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(ms).setSocketTimeout(ms).setConnectTimeout(ms).build();
            method.setConfig(requestConfig);
        }
        if (header != null) header.forEach(method::setHeader);
        HttpResponse result;
        try {
            result = httpClient.execute(method);
        } catch (IOException e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
        assert result != null;
        log.debug(result.toString());
        if (result.getStatusLine().getStatusCode() == 200) {
            try {
                message = EntityUtils.toString(result.getEntity(), UTF_8);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            message = result.toString();
        }
        return message;
    }
}
