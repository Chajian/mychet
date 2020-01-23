package com.yezi.chet.community.socket.operation;

import com.yezi.chet.community.socket.BaseSocketSender;
import com.yezi.chet.community.socket.BaseSocketServer;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.user.User;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

import java.net.Socket;
import java.sql.SQLException;

/**
 *  主要实现功能为登陆模块
 * 包含一下功能[以后可以补充]，登陆验证,发送数据包反馈内容。
 */
public class LoginOperation extends BaseOperation {

    public LoginOperation(PersonExcuteSqlLite excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public void opeartion(Socket socket,ApplicationData data) {
        User user = data.getUser();
        try {
            if (excuteSqlLite.isBeing(user.getAccount())&&!excuteSqlLite.isOnline(user.getAccount())) {
                excuteSqlLite.setOnline(user.getAccount(),1);
                BaseSocketServer.getCommunity().put(user.getAccount(),socket);
                BaseSocketSender.getSocketSender().sendSucceff(user.getAccount(),data.getType());
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("有一个socket在登陆中发生了异常");
        }
    }
}
