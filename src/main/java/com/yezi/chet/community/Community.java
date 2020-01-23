package com.yezi.chet.community;

import java.io.IOException;
import java.net.Socket;

/**
 *  服务器接口
 */
public interface Community {

    boolean createCommunity();//创建服务器连接

    void put(String name, Socket socket);
}
