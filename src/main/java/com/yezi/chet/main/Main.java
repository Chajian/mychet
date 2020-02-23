package com.yezi.chet.main;

import com.yezi.chet.community.netty.BaseSocketServer;

public class Main {
    public static BaseSocketServer server;
    public static void main(String[] args){
        server =BaseSocketServer.getCommunity();
        BaseSocketServer.getCommunity();
    }
}
