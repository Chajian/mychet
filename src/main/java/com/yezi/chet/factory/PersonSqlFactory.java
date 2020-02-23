package com.yezi.chet.factory;

import com.yezi.chet.sql.BaseCreateSql;
import com.yezi.chet.sql.BaseExcuteSql;
import com.yezi.chet.sql.sqlite.person.PersonCreateSqlLite;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

import java.sql.Connection;

/**
 *  玩家数据库对象工厂
 * @author yezi
 */
public class PersonSqlFactory implements BaseSqlFactory{

    @Override
    public BaseCreateSql getCreateSql(String url, String sql_name, String sql_commend) {
        return new PersonCreateSqlLite(url, sql_name);
    }
    public PersonCreateSqlLite getCreateSql() {
        return new PersonCreateSqlLite("jdbc:sqlite:mypersons.db", "mypersons");
    }

    @Override
    public BaseExcuteSql getExcuteSql(Connection connection, String table,String friends_table,String
                                       person_table) {
        return new PersonExcuteSqlLite(connection, table,friends_table,person_table);
    }


    public PersonExcuteSqlLite getExcuteSql(Connection connection,String friends_table,String person_table){
        return new PersonExcuteSqlLite(connection, "Persons",friends_table,person_table);
    }
}
