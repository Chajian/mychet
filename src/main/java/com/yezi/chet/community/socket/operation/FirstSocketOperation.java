package com.yezi.chet.community.socket.operation;

import com.yezi.chet.community.socket.OperationSocket;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

import java.net.Socket;

/**
 * @deprecated 处理第一次连接并将唯一id发给客户
 */
public class FirstSocketOperation extends BaseOperation {

    public FirstSocketOperation(PersonExcuteSqlLite excuteSqlLite) {
        super(excuteSqlLite);
    }




}