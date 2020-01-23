package com.yezi.chet.factory;

import com.yezi.chet.sql.BaseCreateSql;
import com.yezi.chet.sql.BaseExcuteSql;

import java.sql.Connection;

/**
 *  创建sql对象的工厂接口
 * @author yezi
 */
public interface BaseSqlFactory {

    BaseCreateSql getCreateSql(String url, String sql_name, String sql_commend);

    BaseExcuteSql getExcuteSql(Connection connection, String table);

}
