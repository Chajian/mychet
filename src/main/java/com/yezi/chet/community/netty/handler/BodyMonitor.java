package com.yezi.chet.community.netty.handler;

import com.yezi.chet.community.operation.*;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.MyBatis;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BodyMonitor extends ChannelInboundHandlerAdapter {

    private BaseOperation baseOperation;
    private MyBatis excuteSqlLite;

    public BodyMonitor(MyBatis excuteSqlLite) {
        this.excuteSqlLite = excuteSqlLite;
        baseOperation = new BaseOperation(excuteSqlLite.getChetDao());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SendInfo data = (SendInfo)msg;
        int type = Permission.NULL;
        switch (data.getData().getType()){
            case Permission.COMMUNITY_LOGIN:
                baseOperation = new LoginOperation(excuteSqlLite.getChetDao());
                break;

            case Permission.COMMUNITY_REGISTER:
                baseOperation = new RegisterOperation(excuteSqlLite.getChetDao());
                break;

            case Permission.ADD_FRIEND:
                baseOperation = new AddFriendOperation(excuteSqlLite.getChetDao());
                break;

            case Permission.SEND_MESSAGE_FRIENDS:
                baseOperation = new FriendMessageOperation(excuteSqlLite.getChetDao());
                break;

            case Permission.GET_ALL_THING:
                baseOperation = new GetAllThingOpeartion(excuteSqlLite.getChetDao(),ctx);
                break;

            case Permission.SEARCH_ADD_FRIENDS:
                baseOperation = new SearchFriendsOpeartion(excuteSqlLite.getChetDao());
                break;

            case Permission.GET_FRIENDS_INFO:
                baseOperation = new GetFriendsOpeartion(excuteSqlLite.getChetDao());
                break;


            case Permission.COMMUNITY_TRY:
                baseOperation = new TryCommunityOpeartion(excuteSqlLite.getChetDao());
                break;

            case Permission.ADD_FRIEND_AGREE:
                baseOperation = new AddFriendAgreeOperation(excuteSqlLite.getChetDao());
                break;
        }
        int success = baseOperation.opeartion(data);
        if(success != Permission.NULL) {
            if(success == Permission.FAIL){
                data.getData().setType(success);
            }else {
                excuteSqlLite.commit();
            }
            ctx.writeAndFlush(data);
        }
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
