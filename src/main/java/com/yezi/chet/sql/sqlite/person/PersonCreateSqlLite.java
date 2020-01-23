package com.yezi.chet.sql.sqlite.person;

import com.yezi.chet.sql.BaseExcuteSql;
import com.yezi.chet.sql.sqlite.CreateSqlLite;

import java.sql.Connection;

public class PersonCreateSqlLite extends CreateSqlLite {

    Connection connection;
    String table_name = null;

    public PersonCreateSqlLite(String url, String sql_name) {
        super(url, sql_name);
        setSql_command(
                "CREATE TABLE IF NOT EXISTS Persons(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "account VARCHAR(15) DEFAULT NULL," +
                "password VARCHAR(18) DEFAULT NULL," +
                "photo MEDIUMbLOb," +
                "info TEXT DEFAULT '0'," +
                "online TINYIN,"+
                "friends TEXT,"+
                "registertime TEXT)"
        );//设置sql创建数据库表的指令

        connection = create();
        table_name = "Persons";
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public String getTable_name() {
        return table_name;
    }
}
