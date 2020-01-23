package com.yezi.chet.sql;

import java.sql.Connection;


/**
 *  数据库创造基础接口
 * @author yezi
 */
public interface BaseCreateSql {
    //创建数据库连接
    Connection create();

}
