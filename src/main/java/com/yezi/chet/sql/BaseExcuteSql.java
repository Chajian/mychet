package com.yezi.chet.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  数据库基础执行接口
 * @author yezi
 */
public interface BaseExcuteSql {

    boolean add(String[] marks,Object[] values)throws SQLException;//添加数据
    boolean delete(String[] marks,Object[] values)throws SQLException;//删除数据
    boolean change(String[] marks,Object[] values ,String where)throws SQLException;
    ResultSet find(String[] marks, Object[] values)throws SQLException;

}
