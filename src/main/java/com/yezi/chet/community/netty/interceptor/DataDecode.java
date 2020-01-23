package com.yezi.chet.community.netty.interceptor;

import com.yezi.chet.tools.ByteBufToBytes;
import com.yezi.chet.tools.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Data解码器
 */
public class DataDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        ByteBufToBytes byteBufToBytes = new ByteBufToBytes();
        byte[] bytes = byteBufToBytes.read(byteBuf);
        Object o = ByteObjConverter.byteToObject(bytes);
        list.add(o);
    }
}
