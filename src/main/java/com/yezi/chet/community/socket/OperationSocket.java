package com.yezi.chet.community.socket;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

import java.net.Socket;

public interface OperationSocket {

    void opeartion(Socket socket,ApplicationData data);

}
