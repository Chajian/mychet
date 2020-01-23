package com.yezi.chet.sql.sqlite.person;

import com.yezi.chet.data.user.User;
import com.yezi.chet.sql.sqlite.ExcuteSqlLite;
import com.yezi.chet.tools.ConversionStringUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  处理sqlite的用户对象拓展功能
 */
public class PersonExcuteSqlLite extends ExcuteSqlLite {

    public PersonExcuteSqlLite(Connection connection, String table) {
        super(connection, table);

    }

    //通过账号获取对象
    public synchronized User getUser(String account) throws SQLException {
        User user = null;
        String[] marks = new String[]{"account"};//获取用户的数据
        if(account!=null){
            ResultSet resultSet = find(marks,new String[]{account});
            user = new User(ConversionStringUser.ChangeUserInfo(resultSet.getString("info")),
                    resultSet.getString("account"), resultSet.getString("password"));
            user.setFriends(ConversionStringUser.ChangeUserFriends(resultSet.getString("friends")));
        }
        return user;
    }

    //检查用户
    public synchronized boolean checkUser(String account,String pass) throws SQLException {
        ResultSet resultSet = null;
        if(account!=null&&pass!=null)
            resultSet = find(new String[]{"account","password"},new String[]{account,pass});
        return resultSet.first();
    }

    /**
     *  判断账号是否注册
     * @param account 账号
     * @return 存在返回true，否则返回false
     */
    public synchronized boolean isBeing(String account)throws SQLException{
        if(account!=null){
            ResultSet resultSet = find(new String[]{"account"},new String[]{account});
            return resultSet.first();
        }
        return false;
    }

    //注册用户
    public synchronized boolean registerUser(User user)throws SQLException{
        if(user!=null && !isBeing(user.getAccount())){

            String account = user.getAccount();
            String password = user.getPassword();
            String info = ConversionStringUser.ChangeUserInfo(user.getInfo());
            String friends = ConversionStringUser.ChangeUserFriends(user.getFriends());

            String[] marks = new String[]{"account","password","info","friends"};
            return add(marks,new Object[]{account,password,info,friends});
        }
        return false;
    }

    //通过账号删除用户
    public synchronized boolean deleteUser(String account)throws SQLException{

        if(isBeing(account)){
            return delete(new String[]{"account"},new String[]{account});
        }
        return false;
    }

    //从数据库中判断用户是否在线
    public synchronized boolean isOnline(String account)throws SQLException{
        User user = getUser(account);
        return user.isOnline();
    }

    //设置用户在线状态
    public synchronized void setOnline(String account,int online)throws SQLException{
        change(new String[]{"online"},new Object[online],"WHERE account = "+account);
    }

    //将对方互相添加为好友
    public synchronized boolean addFriend(String requester,String agreer)throws SQLException{
        ResultSet resultSet_requester = find(new String[]{"account"},new String[]{requester});
        ResultSet resultset_agreer = find(new String[]{"account"},new String[]{agreer});
        if(resultSet_requester.first()&& resultset_agreer.first()){
            String requester_friends = resultSet_requester.getString("friends");
            String agreer_friends = resultset_agreer.getString("friends");
            if(!requester_friends.contains(agreer)&&!agreer_friends.contains(requester)){
                //如果同意者不是请求者的好友,将彼此添加到对方的好友列表
                requester_friends += ","+agreer;
                agreer_friends += ","+requester;
                change(new String[]{"friends"},new String[]{requester_friends},"WHERE account = "+requester);
                change(new String[]{"friends"},new String[]{agreer_friends},"WHERE account = "+agreer);
                return true;
            }
        }
        return false;
    }
}
