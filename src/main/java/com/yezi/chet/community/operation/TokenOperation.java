package com.yezi.chet.community.operation;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;
import com.yezi.chet.tools.TokenUtil;

/**
 * token码的生成工具
 */
public class TokenOperation extends BaseOperation{

    public TokenOperation(PersonExcuteSqlLite excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion(ApplicationData data) {
        data.setToken(TokenUtil.sign(data.getUser().getAccount(),data.getUser().getPassword()));
        return Permission.SUCCEFF;
    }
}
