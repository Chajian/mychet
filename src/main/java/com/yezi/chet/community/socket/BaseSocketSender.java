package com.yezi.chet.community.socket;

import com.yezi.chet.community.Sender;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *  sender接口的实现，用于发送数据包给客户,并且能够缓存离线发给好友
 */
public class BaseSocketSender implements Sender,Runnable {

    public static BaseSocketSender socketSender;

    List<SendInfo> sockets = new ArrayList<SendInfo>();//存放所有包的队列

    private BaseSocketSender() {
    }

    @Override
    public void run() {
        while (true){
            try {
//                System.out.println("发送数据包");
                Thread.yield();
                sendOperation();
            }
            catch (IOException e){
                e.printStackTrace();
                System.out.println("发送数据包时出错!");
            }
        }
    }

    @Override
    public void put(SendInfo info) {
        sockets.add(info);
    }

    public static BaseSocketSender getSocketSender() {
        if(socketSender == null)
            socketSender = new BaseSocketSender();
        return socketSender;
    }

    //发送成功包
    public void sendSucceff(String reciver_name,String type){
        ApplicationData data =  ApplicationData.getData(Permission.SUCCEFF);
        data.setInfo(new String[]{null,null,type});
        SendInfo sendInfo = new SendInfo(reciver_name,data);
        sockets.add(sendInfo);
    }

    //发送成功包
    public void sendSucceff(String reciver_name,int type){
        ApplicationData data = ApplicationData.getData(Permission.SUCCEFF);
        data.setInfo(new String[]{null,null,String.valueOf(type)});
        System.out.println(data.getInfo()[2]);
        SendInfo sendInfo = new SendInfo(reciver_name,data);
        sockets.add(sendInfo);
    }

    //处理发送数据的包
    public synchronized void sendOperation() throws IOException,NullPointerException{
        Iterator iterator = sockets.iterator();
        while(iterator.hasNext()){
            SendInfo info = (SendInfo) iterator.next();
            Socket revier = BaseSocketServer.getCommunity().getSocketFromAccount(info.getRevier_account());
            Socket sender = null;
            if(info.getSender_account() != null)
                sender = BaseSocketServer.getCommunity().getSocketFromAccount(info.getSender_account());
            ApplicationData data = info.getData();
            if(revier != null && data != null&& !revier.isClosed()){
                ObjectOutputStream outputStream = new ObjectOutputStream(revier.getOutputStream());
                outputStream.writeObject(data);
                outputStream.writeObject(null);
                iterator.remove();
                System.out.println("发送成功!");
                if(sender != null)
                    sendSucceff(info.getSender_account(),Integer.parseInt(data.getInfo()[2]));
            }
        }
    }

//    //发送失败包
//    public void sendFail(Socket socket){
//
//    }

    public void sendMessageFriend(Socket socket,String account,String rever){

    }
}
