package com.yezi.chet.community.netty.handler;

import com.yezi.chet.community.operation.BaseOperation;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.tools.TokenUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 身份验证拦截器
 */
public class HeadMonitor extends ChannelInboundHandlerAdapter {

    BaseOperation baseOperation;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //处理身份验证
        SendInfo data = (SendInfo) msg;
        if(data.getData().getType() == Permission.COMMUNITY_LOGIN||data.getData().getType()==Permission.COMMUNITY_REGISTER||data.getData().getType()==Permission.COMMUNITY_TRY){

        }
        else{
            boolean verify = TokenUtil.verify(data.getData().getToken());
            if(!verify) {
                return;
            }
        }
        ctx.fireChannelRead(data);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
