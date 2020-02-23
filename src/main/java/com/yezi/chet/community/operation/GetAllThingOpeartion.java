package com.yezi.chet.community.operation;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.data.user.Message;
import com.yezi.chet.sql.dao.ChetDao;
import io.netty.channel.ChannelHandlerContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllThingOpeartion extends BaseOperation {

    ChannelHandlerContext channelHandlerContext;

    public GetAllThingOpeartion(ChetDao excuteSqlLite, ChannelHandlerContext channelHandlerContext) {
        super(excuteSqlLite);
        this.channelHandlerContext = channelHandlerContext;
    }

    @Override
    public int opeartion(SendInfo data) {
        List<String> list = null;
        try {
            //Friends
            list = excuteSqlLite.getFriendsRequest(data.getData().getUser().getAccount());
            List<Friend> friends = new ArrayList<>();
            for(String account:list){
                Friend f = new Friend(account,"", null);
                friends.add(f);
            }
            data.setData2(friends);
            data.getData().setType2(Permission.GET_FRIENDS_INFO);

            //Message
            List<Message> messages = excuteSqlLite.getMessages(data.getData().getUser().getAccount());
            if(messages!=null && messages.size() >0) {
                ApplicationData data1 = new ApplicationData();
                data1.setType2(Permission.SEND_MESSAGE_FRIENDS);
                data1.setType(Permission.GET_ALL_THING);
                data1.setUser(data.getData().getUser());
                SendInfo info = new SendInfo(data.getData().getUser().getAccount(), data1);
                info.setData2(friends);
                channelHandlerContext.writeAndFlush(info);
            }
            System.out.println(getClass().toString()+"获取了所有信息哦!");
            return Permission.SUCCEFF;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Permission.FAIL;
    }
}
