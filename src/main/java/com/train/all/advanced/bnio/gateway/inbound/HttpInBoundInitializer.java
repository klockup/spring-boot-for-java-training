package com.train.all.advanced.bnio.gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

/**
 *  通道初始化
 * @author: kennys.chai
 * @date: 2022/1/11
 */
public class HttpInBoundInitializer extends ChannelInitializer<SocketChannel> {

    private List<String> proxyServerList;

    public HttpInBoundInitializer(List<String> proxyServerList){
        this.proxyServerList = proxyServerList;
    }


    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline channelPipeline=ch.pipeline();
        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast(new HttpObjectAggregator(1024*1024));
        channelPipeline.addLast(new HttpInBoundHandler(this.proxyServerList));

    }
}
