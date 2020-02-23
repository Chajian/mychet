package com.yezi.chet.community.netty.interceptor;

import com.yezi.chet.tools.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Data编码器
 */
public class DataEncode extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        byte[] datas = ByteObjConverter.objectToByte(o);
        byteBuf.writeInt(datas.length);
        byteBuf.writeBytes(datas);
        channelHandlerContext.flush();
    }
}
