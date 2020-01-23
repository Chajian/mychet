package com.yezi.chet.community.socket.operation;

import com.yezi.chet.community.socket.OperationSocket;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

import java.net.Socket;

public class BaseOperation implements OperationSocket {

    PersonExcuteSqlLite excuteSqlLite = null;

    public BaseOperation(PersonExcuteSqlLite excuteSqlLite) {
        this.excuteSqlLite = excuteSqlLite;
    }

    @Override
    public void opeartion(Socket socket, ApplicationData data) {

    }
}
