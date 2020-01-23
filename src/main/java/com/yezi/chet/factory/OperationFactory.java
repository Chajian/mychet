package com.yezi.chet.factory;

import com.yezi.chet.community.socket.operation.*;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

public class OperationFactory {

    public static OperationFactory operationFactory;

    public static OperationFactory getOperationFactory() {
        if(operationFactory == null)
            operationFactory = new OperationFactory();
        return operationFactory;
    }

    public LoginOperation getLoginOperation(PersonExcuteSqlLite excuteSqlLite){
        return new LoginOperation(excuteSqlLite);
    }

    public RegisterOperation getRegisterOperation(PersonExcuteSqlLite excuteSqlLite){
        return new RegisterOperation(excuteSqlLite);
    }

    public AddFriendOperation getAddFriendOperation(PersonExcuteSqlLite excuteSqlLite){
        return new AddFriendOperation(excuteSqlLite);
    }

    public AddFriendAgreeOperation getAddFriendAgreeOperation(PersonExcuteSqlLite excuteSqlLite){
        return new AddFriendAgreeOperation(excuteSqlLite);
    }

    public FriendMessageOperation getFriendMessageOperation(PersonExcuteSqlLite excuteSqlLite){
        return new FriendMessageOperation(excuteSqlLite);
    }
}