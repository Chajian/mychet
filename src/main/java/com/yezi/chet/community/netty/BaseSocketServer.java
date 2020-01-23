package com.yezi.chet.community.netty;

import com.yezi.chet.community.Community;
import com.yezi.chet.community.netty.handler.InitializerMonitor;
import com.yezi.chet.sql.sqlite.person.PersonSqlLite;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.net.Socket;

public class BaseSocketServer implements Community {

    private PersonSqlLite personSqlLite;
    private static BaseSocketServer server = null;
    private ServerBootstrap serverBootstrap;
    private EventLoopGroup parent;
    private EventLoopGroup child;
    private InitializerMonitor initializerMonitor;
    private int port = 8082;

    public BaseSocketServer() throws InterruptedException {
        personSqlLite = new PersonSqlLite();
        serverBootstrap = new ServerBootstrap();
        initializerMonitor = new InitializerMonitor(personSqlLite.getExcuteSql());
        parent = new NioEventLoopGroup();
        child = new NioEventLoopGroup();

        serverBootstrap.group(parent,child)
            .channel(NioServerSocketChannel.class)
            .childHandler(initializerMonitor)
            .localAddress(new InetSocketAddress(port));

        ChannelFuture f = serverBootstrap.bind().sync();
        f.channel().closeFuture().sync();
    }

    @Override
    public boolean createCommunity() {
        return false;
    }

    @Override
    public void put(String name, Socket socket) {

    }

    public static BaseSocketServer getServer() {
        if(server == null) {
            try {
                server = new BaseSocketServer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return server;
    }
}
