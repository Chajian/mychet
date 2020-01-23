package com.yezi.chet.community.socket.operation;

import com.yezi.chet.community.socket.BaseSocketSender;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

import java.net.Socket;

/**
 *  添加好友的实现
 */
public class AddFriendOperation extends BaseOperation {

    public AddFriendOperation(PersonExcuteSqlLite excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public void opeartion(Socket socket, ApplicationData data) {
        String revier_name = data.getInfo()[1];
        SendInfo sendInfo = new SendInfo(revier_name,data);
        sendInfo.setSender_account(data.getUser().getAccount());
        BaseSocketSender.getSocketSender().put(sendInfo);
    }
}
