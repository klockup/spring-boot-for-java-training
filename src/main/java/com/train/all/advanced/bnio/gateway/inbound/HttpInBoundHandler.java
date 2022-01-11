package com.train.all.advanced.bnio.gateway.inbound;

import com.train.all.advanced.bnio.gateway.filter.HeaderHttpRequestFilter;
import com.train.all.advanced.bnio.gateway.filter.HttpRequestFilter;
import com.train.all.advanced.bnio.gateway.outbound.httpclient4.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class HttpInBoundHandler extends ChannelInboundHandlerAdapter {


    private List<String> proxyServerList;
    private HttpOutboundHandler handler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();


    public HttpInBoundHandler(List<String> proxyServerList){
        this.proxyServerList=proxyServerList;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
        this.handler = new HttpOutboundHandler(this.proxyServerList);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            handler.handle(fullRequest,ctx,filter);

        }finally {
            ReferenceCountUtil.release(msg);

        }



    }
}
