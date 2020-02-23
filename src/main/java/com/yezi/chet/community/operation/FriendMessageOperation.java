package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Message;
import com.yezi.chet.sql.dao.ChetDao;

/**
 *  给好友发信息的实现
 */
public class FriendMessageOperation extends BaseOperation {

    public FriendMessageOperation(ChetDao excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion(SendInfo data) {
        Message message = (Message) data.getData2();
        if(message!=null)
            excuteSqlLite.insertMessage(message);
        return Permission.SUCCEFF;
    }
}
