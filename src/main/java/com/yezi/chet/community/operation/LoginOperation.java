package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.tool.Tool;
import com.yezi.chet.data.user.User;
import com.yezi.chet.sql.dao.ChetDao;

import java.sql.SQLException;

/**
 *  主要实现功能为登陆模块
 * 包含一下功能[以后可以补充]，登陆验证,发送数据包反馈内容。
 */
public class LoginOperation extends BaseOperation {

    public LoginOperation(ChetDao excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion(SendInfo data) {
        User user = data.getData().getUser();
        try {
            if (excuteSqlLite.isBeing(user.getAccount())
//                    &&!excuteSqlLite.isOnline(user.getAccount())
            )
            {
                if(excuteSqlLite.checkUser(user.getAccount(),user.getPassword())) {
                    excuteSqlLite.setOnline(user.getAccount(), 1);
                    //生成校验码
                    new TokenOperation(excuteSqlLite).opeartion(data);
                    User user_1 = excuteSqlLite.getUser(user.getAccount());
//                    excuteSqlLite.getPicture(user.getAccount());
                    user_1.setPhotoOne(Tool.fileToBytes(user_1.getAccount()));
                    if(user_1!=null){
                        data.getData().setUser(user_1);
                    }
                    return Permission.SUCCEFF;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("有一个socket在登陆中发生了异常");
        }
        return Permission.FAIL;
    }
}
