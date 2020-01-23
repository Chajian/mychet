package com.yezi.chet.community.socket;

import com.yezi.chet.community.Monitor;
import com.yezi.chet.community.SocketMonitor;
import com.yezi.chet.community.socket.operation.BaseOperation;
import com.yezi.chet.community.socket.operation.LoginOperation;
import com.yezi.chet.community.socket.operation.RegisterOperation;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.User;
import com.yezi.chet.sql.sqlite.person.PersonExcuteSqlLite;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.TooManyListenersException;

/**
 *  socket监听者
 * 负责socke的连接种类分流等
 *
 */
public class CommunityMonitor implements Monitor ,Runnable{

    ServerSocket server;
    SocketMonitor socketMonitor = null;
    BaseOperation baseOperation;
    PersonExcuteSqlLite excuteSqlLite;

    public CommunityMonitor(ServerSocket server, PersonExcuteSqlLite excuteSqlLite) {
        this.server = server;
        this.excuteSqlLite = excuteSqlLite;
        baseOperation = new BaseOperation(excuteSqlLite);
    }


    @Override
    public void Listen() {
        try {
            //处理第一次的连接
            FirstSocket();
            OperationTrades();
            Thread.yield();
        }
        catch (IOException | ClassNotFoundException | InterruptedException e){
            e.printStackTrace();
            System.out.println("数据分流出现问题");
        }
    }

    @Override
    public void run() {
        while(true){
            Listen();
//            System.out.println("开始分流");
        }
    }

    //处理游客池中的socket，进行数据分流
    public synchronized void OperationTrades()throws IOException,ClassNotFoundException,InterruptedException {
        Iterator iterator = BaseSocketServer.getCommunity().getSockets();
        while (iterator.hasNext()) {
            Socket socket = (Socket) iterator.next();
            if (!socket.isClosed()) {
                InputStream inputStream = socket.getInputStream();
                //下面一行出现爆粗，可能因为FirstSocketMonitor里面也有objectinputstream，建议注释掉试试
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Thread.sleep(100);
                if (objectInputStream.readObject() != null) {
                    ApplicationData data = (ApplicationData) objectInputStream.readObject();//获取数据
                    System.out.println(data.type);
                    if (data.getInfo() != null)
                        System.out.println(data.getInfo().length);
                    switch (data.type) {
                        case Permission.COMMUNITY_LOGIN:
                            baseOperation = new LoginOperation(excuteSqlLite);
//                        iterator.remove();//执行完之后删除这个游客池中的socket,防止登陆之后，
                            // 游客池中的socket和连接池中的socket冲突
                            break;

                        case Permission.COMMUNITY_REGISTER:
                            System.out.println("sjdfkjs");
                            baseOperation = new RegisterOperation(excuteSqlLite);
                            break;

                        case Permission.COMMUNITY_TRY:

                            break;
                    }
                    baseOperation.opeartion(socket, data);//处理用户的行动
                }
            }
        }
    }


    //处理第一次的连接
    public synchronized void FirstSocket(){
        try {
            Socket socket = server.accept();
            Thread.sleep(10);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ApplicationData data = (ApplicationData) objectInputStream.readObject();
            if (data.type == Permission.COMMUNITY_TRY) {//如果发送的数据包类型为尝试建立第一次连接
                User user = data.getUser();
                BaseSocketServer.getCommunity().put(user.getAccount(), socket);//将游客的连接加入连接池
                BaseSocketSender.getSocketSender().sendSucceff(user.getAccount(), data.type);
            } else//否则直接关闭socket连接
                socket.close();//
            System.out.println("有一位游客加入了聊天");
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("获取用户数据失败");
        }
//        Iterator keys = BaseSocketServer.getCommunity().getSocketsMap().keySet().iterator();
//        while (keys.hasNext()){
//            ChetThreadPool.getChetThreadPool().putRunnable(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        String account = (String) keys.next();
//                        Socket socket = BaseSocketServer.getCommunity().getSocketFromAccount(account);
//                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
//                        ApplicationData data = (ApplicationData) inputStream.readObject();
//
//                        switch (data.type){
//                            case Permission.ADD_FRIEND:
//                                baseOperation = OperationFactory.getOperationFactory().getAddFriendOperation(excuteSqlLite);
//                                break;
//
//                            case Permission.ADD_PUBLIC_CHET:
//
//                                break;
//
//                            case Permission.SEND_CIRCLE:
//
//                                break;
//
//                            case Permission.SEND_MESSAGE_FRIENDS:
//                                baseOperation = OperationFactory.getOperationFactory().getFriendMessageOperation(excuteSqlLite);
//                                break;
//
//                            case Permission.SEND_MESSAGE_PUBLIC:
//
//                                break;
//
//                            case Permission.ADD_FRIEND_AGREE:
//                                baseOperation = OperationFactory.getOperationFactory().getAddFriendAgreeOperation(excuteSqlLite);
//                                break;
//
//                            case Permission.ADD_FRIEND_DISAGREE:
//
//                                break;
//                        }
//                        baseOperation.opeartion(socket,data);
//                    }
//                    catch (IOException | ClassNotFoundException e){
//                        e.printStackTrace();
//                        System.out.println("进行用户数据分流出现问题");
//                    }
//                }
//            });
//        }
    }
}
