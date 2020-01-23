package com.yezi.chet.community.socket;

import com.yezi.chet.community.SocketMonitor;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @deprecated 即将启用，将第一次连接转到monitor里面
 *  封装第一次用户连接的行为
 */
public class FirstSocketMonitor implements SocketMonitor,Runnable{

    public static ObjectInputStream object;
    ServerSocket serverSocket;

    public FirstSocketMonitor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    //接受每一位连接的请求，判断获取的数据包是否正确。判断数据包无误之后，再判断是否已经有人登陆。如果没有人占用账号，就返回成功包
    //，否则断开连接。
    @Override
    public void run() {
        while(true){
            try {
                monitor(null);
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                e.printStackTrace();
                System.out.println("与用户的首次连接发生了一个故障!");
            }
        }
    }

    @Override
    public synchronized void monitor(Socket socket1) {

    }
}
