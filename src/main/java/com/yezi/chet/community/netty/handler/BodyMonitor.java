package com.yezi.chet.community.netty.handler;

import com.yezi.chet.community.operation.BaseOperation;
import com.yezi.chet.community.operation.LoginOperation;
import com.yezi.chet.community.operation.RegisterOperation;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BodyMonitor extends ChannelInboundHandlerAdapter {

    private BaseOperation baseOperation;
    private PersonExcuteSqlLite excuteSqlLite;

    public BodyMonitor(PersonExcuteSqlLite excuteSqlLite) {
        this.excuteSqlLite = excuteSqlLite;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ApplicationData data = (ApplicationData)msg;
        switch (data.type){
            case Permission.COMMUNITY_LOGIN:
                baseOperation = new LoginOperation(excuteSqlLite);
                break;

            case Permission.COMMUNITY_REGISTER:
                baseOperation = new RegisterOperation(excuteSqlLite);
                break;
        }
        int type = baseOperation.opeartion(data);
        data.setType(type);
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
