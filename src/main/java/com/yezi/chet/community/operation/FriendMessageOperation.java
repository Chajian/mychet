package com.yezi.chet.community.operation;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.user.User;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

import java.net.Socket;

/**
 *  给好友发信息的实现
 */
public class FriendMessageOperation extends BaseOperation {

    public FriendMessageOperation(PersonExcuteSqlLite excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion( ApplicationData data) {
        User user = data.getUser();
        String reciver_name = data.getInfo()[1];//接受者的账号
        SendInfo sendInfo = new SendInfo(reciver_name,data);
        sendInfo.setSender_account(user.getAccount());
        BaseSocketSender.getSocketSender().put(sendInfo);//转发数据包
    }
}
