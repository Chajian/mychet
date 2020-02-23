package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.dao.ChetDao;

import java.sql.SQLException;

/**
 *  添加好友的实现
 */
public class AddFriendOperation extends BaseOperation {

    public AddFriendOperation(ChetDao excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion( SendInfo data) {
        System.out.println(getClass().toString()+"打算添加好友了哦!");
        try {
            if(excuteSqlLite.isBeing(data.getRevier_account())&&excuteSqlLite.isBeing(data.getSender_account())) {//如果接收者和发送着存在
                data.getData().setType(Permission.ADD_FRIEND);
                if(!excuteSqlLite.isRequest(data.getData().getUser().getAccount(),data.getRevier_account()))
                    excuteSqlLite.addFriendRequest(data.getSender_account(),data.getRevier_account());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Permission.SUCCEFF;
    }
}
