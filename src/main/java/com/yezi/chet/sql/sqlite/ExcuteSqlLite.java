package com.yezi.chet.sql.sqlite;

import com.yezi.chet.sql.BaseExcuteSql;

import java.sql.*;

/**
 *  BaseExcuteSql接口的实现累，实现了基础的增删查改
 * @author yezi
 */
public class ExcuteSqlLite implements BaseExcuteSql {

    Connection connection;
    String table = null;

    public ExcuteSqlLite(Connection connection,String table) {
        this.connection = connection;
        this.table = table;
    }

    @Override
    public synchronized boolean add(String[] marks, Object[] values)throws SQLException {
        //建立sql添加字符串
        if(marks.length==values.length&&marks.length>0) {
            String command = "INSERT INTO " + table + "(";
            String string_mark = "";
            String string_values = "";
            for (int i = 1; i < marks.length; i++) {
                    string_mark += marks[i-1] + ",";
                    string_values += "?,";
            }
            string_mark += marks[marks.length] + ")";
            string_values += "?)";
            command += string_mark + "VALUES(" + string_values;

            //执行sql指令
            PreparedStatement statement = connection.prepareStatement(command);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.execute();
            statement.close();
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean delete(String[] marks, Object[] values)throws SQLException {
        if(marks!=null && marks.length == values.length){
            String command = "DELETE * FROM "+table+" where ";
            for(int i = 1 ; i < marks.length ; i++){
                command += marks[i-1]+"=? and ";
            }
            command += marks[marks.length]+"=?";

            PreparedStatement statement = connection.prepareStatement(command);
            for(int i = 0 ; i < values.length ; i++){
                statement.setObject(i+1,values[i]);
            }
            statement.execute();
            statement.close();
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean change(String[] marks, Object[] values ,String where)throws SQLException {
        if(marks!=null && marks.length == values.length){
            String command = "UPDATE "+table+" SET ";
            for(int i = 1 ; i < marks.length ; i++){
                command += marks[i-1]+"=?,";
            }
            command += marks[marks.length] +"=? ";
            command += where;

            PreparedStatement statement = connection.prepareStatement(command);
            for(int i = 0 ; i < values.length ; i++){
                statement.setObject(i+1, values[i]);
            }
            statement.execute();
            statement.close();
            return true;
        }
        return false;
    }

    @Override
    public synchronized ResultSet find(String[] marks, Object[] values)throws SQLException {
        if(marks!= null &&marks.length == values.length){
            //建立sql查询指令
            String command = "SELECT * FROM "+table+" WHERE ";
            for(int i = 1 ; i < marks.length ; i++){
                command += marks[i-1]+"=? and ";
            }
            command += marks[marks.length]+"=?";

            //补充where条件
            PreparedStatement statement = connection.prepareStatement(command);
            for(int i = 0 ; i < values.length ; i++){
                statement.setObject(i+1, values[i]);
            }

            //通过执行select指令获取ResultSet对象
            ResultSet resultSet = statement.executeQuery(command);
            return resultSet;
        }
        return null;
    }


}
