package com.yezi.chet.community.operation;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

public class BaseOperation implements Opeartion {

    PersonExcuteSqlLite excuteSqlLite = null;

    public BaseOperation(PersonExcuteSqlLite excuteSqlLite) {
        this.excuteSqlLite = excuteSqlLite;
    }

    @Override
    public int opeartion(ApplicationData data) {
        return Permission.NULL;
    }
}
