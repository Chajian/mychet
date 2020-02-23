package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Message;
import com.yezi.chet.sql.dao.ChetDao;

public class SenderMessageOpeartion extends BaseOperation {

    public SenderMessageOpeartion(ChetDao excuteSqlLite) {
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
