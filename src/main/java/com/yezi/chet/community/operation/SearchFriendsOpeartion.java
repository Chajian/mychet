package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.data.user.User;
import com.yezi.chet.sql.dao.ChetDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchFriendsOpeartion extends BaseOperation {

    public SearchFriendsOpeartion(ChetDao excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion(SendInfo data) {
        String account = data.getRevier_account();
        List<Friend> list = new ArrayList<>();
        try {
            User user = excuteSqlLite.getUser(account);
            Friend friend = new Friend(user.getAccount(),user.getInfo(),user.getPhoto());
            list.add(friend);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.setData2(list);
        return Permission.SUCCEFF;
    }
}
