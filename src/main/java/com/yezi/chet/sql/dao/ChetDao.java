package com.yezi.chet.sql.dao;

import com.yezi.chet.data.user.Message;
import com.yezi.chet.data.user.User;
import org.apache.ibatis.annotations.Param;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public interface ChetDao {

    public User getUser(String id)throws SQLException;

    public Blob getPicture(String account);

    public Boolean checkUser(@Param("account") String account, @Param("pass") String pass) throws SQLException;

    public Boolean isBeing(String account)throws SQLException;

    public Boolean isRequest(@Param("sender_account") String sender_account,@Param("reciver_account") String reciver_account)throws SQLException;

    public void registerUser(User user)throws SQLException;

    public void deleteUser(String account)throws SQLException;

    public Boolean isOnline(String account)throws SQLException;

    public List<Message> getMessages(String account) throws SQLException;

    public void insertMessage(Message message);

    public void setOnline(@Param("account") String account,@Param("online")int online)throws SQLException;

    public List<String> getFriends(String account) throws SQLException;

    public List<String> getFriendsRequest(String account) throws SQLException;

    public void addFriendRequest(@Param("sender_account") String sender_account,@Param("reciver_account")String reciver_account )throws SQLException;

    public void changeFriendStatus(@Param("is_agree") String is_agree,@Param("sender") String sender,@Param("reciver") String reciver);

}
