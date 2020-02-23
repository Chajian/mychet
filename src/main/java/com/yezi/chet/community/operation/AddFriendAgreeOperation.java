package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.dao.ChetDao;

/**
 *  同意好友的添加的实现
 */
public class AddFriendAgreeOperation extends BaseOperation {

    public AddFriendAgreeOperation(ChetDao excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion(SendInfo data) {
        String sender_account = data.getSender_account();
        String reciver_account = data.getRevier_account();//接受者的名字
        excuteSqlLite.changeFriendStatus("1",sender_account, reciver_account);
                //添加好友成功
                //同意之后暂时给俩人互相发送成功包，后续要求头像的请求就添加请求用户信息包
//                BaseSocketSender.getSocketSender().sendSucceff(sender_account, Permission.ADD_FRIEND_AGREE);
//                BaseSocketSender.getSocketSender().sendSucceff(reciver_account,Permission.ADD_FRIEND_AGREE);
        return Permission.SUCCEFF;
    }
}
