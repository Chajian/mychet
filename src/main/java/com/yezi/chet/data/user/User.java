package com.yezi.chet.data.user;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

/**
 *  用户对象
 * @author yezi
 *
 * ------------------用户信息【info列表】解释----------------------
 * current    description        ClassTypes
 * 0            User's name        String
 * 1            Usre's age          int
 * 2            User's summary      String
 * 3            User's brithday     String
 * 4            User's permission   int
 * 5            User's online       int    补充说明[0代表不在线，1代表在线]
 *
 *    提醒  添加一些好友列表等数据，朋友圈等，并且制作一个数据包，可以发信息，朋友圈，加好友等请求
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;//版本号
    public Hashtable<String,Object> info = new Hashtable<>();//存储用户信息
    public String account;//账号
    public String password;//密码
    public List<String> friends = null;

    public User(Hashtable<String, Object> info, String account, String password) {
        this.info = info;
        this.account = account;
        this.password = password;
    }

    //添加用户数据到列表里
    public void addInfo(String name,Object value){
        info.put(name,value);
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }


    public String getAccount() {
        return account;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Hashtable<String, Object> getInfo() {
        return info;
    }

    public String getPassword() {
        return password;
    }

    //获取玩家是否在线状态
    public boolean isOnline(){
        return (int)info.get("online") == 0 ? false:true;
    }
}
