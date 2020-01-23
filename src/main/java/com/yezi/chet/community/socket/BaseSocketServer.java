package com.yezi.chet.community.socket;

import com.yezi.chet.community.Community;
import com.yezi.chet.sql.sqlite.person.PersonSqlLite;
import com.yezi.chet.thread.ChetThreadPool;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  服务器连接的实现
 */
public class BaseSocketServer implements Community {

    public static BaseSocketServer community = null;
    ServerSocket server;
    private static final int port = 892;
    Map<String,Socket> sockets = new ConcurrentHashMap<>();//连接池
    Map<String, ObjectInputStream> inputs = new ConcurrentHashMap<>();//连接池
    PersonSqlLite personSqlLite;

    //服务与Community的一些对象
//    SocketMonitor FirstCommunity;//处理第一次连接的监听者
//    SocketMonitor ReciverCommunity;//处理连接之后进行互动的监听者

    private BaseSocketServer() {

        personSqlLite = new PersonSqlLite();
        if(createCommunity()) {
            ChetThreadPool.getChetThreadPool().putRunnable(new FirstSocketMonitor(server));
            ChetThreadPool.getChetThreadPool().putRunnable(BaseSocketSender.getSocketSender());
//            ChetThreadPool.getChetThreadPool().putRunnable(BaseSocketSender.get);
            ChetThreadPool.getChetThreadPool().putRunnable(new CommunityMonitor(server, personSqlLite.getExcuteSql()));
        }
    }

    //建立服务器连接
    @Override
    public synchronized boolean createCommunity(){
        try {
            server = new ServerSocket(port);
            System.out.println("服务器开启");
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器开启失败");
        }
        return false;
    }

    @Override
    public void put(String name, Socket socket) {
        sockets.put(name,socket);
    }

    public void put(String name, ObjectInputStream inputStream){
        inputs.put(name,inputStream);
    }

    public static BaseSocketServer getCommunity() {
        if(community == null)
            community = new BaseSocketServer();
        return community;
    }

    /**
     *  通过用户账号查询连接池中对应的socket对象
     */
    public Socket getSocketFromAccount(String account){
        return sockets.get(account);
    }

    //返回连接池中的Socket的Iterator对象
    public Iterator<Socket> getSockets(){
        return sockets.values().iterator();
    }

    public Map<String,Socket> getSocketsMap(){
        return sockets;
    }


    public Iterator getInputs() {
        return inputs.values().iterator();
    }
}
