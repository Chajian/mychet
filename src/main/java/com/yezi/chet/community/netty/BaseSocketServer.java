package com.yezi.chet.community.netty;

import com.yezi.chet.community.Community;
import com.yezi.chet.community.netty.handler.InitializerMonitor;
import com.yezi.chet.sql.MyBatis;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.net.Socket;

public class BaseSocketServer implements Community {

    private MyBatis myBatis;
    private static BaseSocketServer server = null;
    private ServerBootstrap serverBootstrap;
    private EventLoopGroup parent;
    private EventLoopGroup child;
    private InitializerMonitor initializerMonitor;
    private int port = 8080;

    public BaseSocketServer() {
        try {
            myBatis = MyBatis.getMyBatis();
            serverBootstrap = new ServerBootstrap();
            initializerMonitor = new InitializerMonitor(myBatis);
            parent = new NioEventLoopGroup();
            child = new NioEventLoopGroup();

            serverBootstrap.group(parent, child)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(initializerMonitor)
                    .option(ChannelOption.MAX_MESSAGES_PER_READ, 50000)
                    .localAddress(new InetSocketAddress(port));

            ChannelFuture f = serverBootstrap.bind().sync();
            f.channel().closeFuture().sync();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean createCommunity() {
        return false;
    }



    @Override
    public void put(String name, Socket socket) {

    }

    public static BaseSocketServer getCommunity() {
        if (server == null)
            server = new BaseSocketServer();
        return server;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("对象被销毁咯");
    }

}
