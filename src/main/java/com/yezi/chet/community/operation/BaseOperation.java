package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.dao.ChetDao;

public class BaseOperation implements Opeartion {

    ChetDao excuteSqlLite = null;

    public BaseOperation(ChetDao excuteSqlLite) {
        this.excuteSqlLite = excuteSqlLite;
    }

    @Override
    public int opeartion(SendInfo data) {
        return Permission.NULL;
    }
}
