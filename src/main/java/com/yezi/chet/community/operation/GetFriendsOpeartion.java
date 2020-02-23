package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.sql.dao.ChetDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetFriendsOpeartion extends BaseOperation {

    public GetFriendsOpeartion(ChetDao excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion(SendInfo data) {

        try {
            String account = data.getData().getUser().getAccount();
            List<String> accounts = excuteSqlLite.getFriends(account);
            List<Friend> friends = new ArrayList<>();
            for(String accc: accounts){
                Friend friend = new Friend(accc,null,null);
                friends.add(friend);
            }
            data.getData().getUser().setFriends(friends);
            System.out.println(getClass().toString()+"获取了信息");
            return Permission.SUCCEFF;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Permission.FAIL;
    }
}
