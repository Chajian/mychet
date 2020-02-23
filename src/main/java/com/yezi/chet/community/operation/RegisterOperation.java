package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.tool.Tool;
import com.yezi.chet.data.user.User;
import com.yezi.chet.sql.dao.ChetDao;

import java.sql.SQLException;

/**
 *  处理用户的注册行为
 */
public class RegisterOperation extends BaseOperation {

    public RegisterOperation(ChetDao excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion(SendInfo data) {
        try {
            User user = data.getData().getUser();
            if (checkUser(user)&&!excuteSqlLite.isBeing(user.getAccount())){
                excuteSqlLite.registerUser(user);//注册账号
                if(user.getPhoto()!=null)
                    Tool.byteToFile(user.getPhoto(),user.getAccount());
                System.out.println("注册成功!");
                return Permission.SUCCEFF;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Permission.FAIL;
    }

    //检查用户是否符合注册规则
    public boolean checkUser(User user){
        if(user != null)
            if(user.getAccount().length() >= 6 && user.getPassword().length() >= 6){
                return true;
            }
        return false;
    }
}
