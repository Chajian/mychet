package com.yezi.chet.sql.sqlite;

import com.yezi.chet.sql.BaseCreateSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *   实现了BaseCreateSql接口的建立数据库连接接口
 * @author  yezi
 * @version 1.0
 */
public abstract class CreateSqlLite implements BaseCreateSql {

    String url = null;//驱动器限定名
    Connection connection = null;
    String name = "root";
    private String pass = "88488848";
    String sql_name = null;//数据库名
    private String sql_command = null;//执行注册的指令

    public CreateSqlLite(String url, String sql_name, String sql_commend) {
        this.url = url;
        this.sql_name = sql_name;
        this.sql_command = sql_commend;
    }

    public CreateSqlLite(String url, String sql_name) {
        this.url = url;
        this.sql_name = sql_name;
    }

    @Override
    public Connection create() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url, name, pass);
            Statement statement = connection.createStatement();
            statement.execute(sql_command);
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return connection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setSql_command(String sql_command) {
        this.sql_command = sql_command;
    }
}
