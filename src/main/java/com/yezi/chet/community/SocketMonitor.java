package com.yezi.chet.community;

import java.net.Socket;

/**
 *  处理socket对象的基础接口
 */
public interface SocketMonitor {

    void monitor(Socket socket);

}
