package com.yezi.chet.community.netty.handler;

import com.yezi.chet.community.netty.interceptor.DataDecode;
import com.yezi.chet.community.netty.interceptor.DataEncode;
import com.yezi.chet.sql.MyBatis;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class InitializerMonitor extends ChannelInitializer<io.netty.channel.Channel> {
    private MyBatis excuteSqlLite;

    public InitializerMonitor(MyBatis excuteSqlLite) {
        this.excuteSqlLite = excuteSqlLite;
    }

    @Override
    protected void initChannel(io.netty.channel.Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //解码器
        pipeline.addFirst(new DataDecode());

        //中间处理器
        pipeline.addLast(new HeadMonitor());
        pipeline.addLast(new BodyMonitor(excuteSqlLite));

        //编码器
        pipeline.addFirst(new DataEncode());
    }
}
