package com.yezi.chet.community;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;

import java.net.Socket;

/**
 *  socket通讯发送者接口
 */
public interface Sender {
    void put(SendInfo info);
}
