package com.yezi.chet.community.socket.operation;

import com.yezi.chet.community.socket.BaseSocketSender;
import com.yezi.chet.community.socket.BaseSocketServer;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

import javax.lang.model.element.NestingKind;
import java.net.Socket;
import java.sql.SQLException;

/**
 *  同意好友的添加的实现
 */
public class AddFriendAgreeOperation extends BaseOperation {

    public AddFriendAgreeOperation(PersonExcuteSqlLite excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public void opeartion(Socket socket, ApplicationData data) {
        String sender_account = data.getUser().getAccount();
        String reciver_account = data.getInfo()[1];//接受者的名字
        try {
            if(excuteSqlLite.addFriend(sender_account, reciver_account)){
                //添加好友成功
                //同意之后暂时给俩人互相发送成功包，后续要求头像的请求就添加请求用户信息包
                BaseSocketSender.getSocketSender().sendSucceff(sender_account, Permission.ADD_FRIEND_AGREE);
                BaseSocketSender.getSocketSender().sendSucceff(reciver_account,Permission.ADD_FRIEND_AGREE);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
