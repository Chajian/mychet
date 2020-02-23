package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.dao.ChetDao;
import com.yezi.chet.tools.TokenUtil;

/**
 * token码的生成工具
 */
public class TokenOperation extends BaseOperation{

    public TokenOperation(ChetDao excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion(SendInfo data) {
        data.getData().setToken(TokenUtil.sign(data.getData().getUser().getAccount(),data.getData().getUser().getPassword()));
        return Permission.SUCCEFF;
    }
}
