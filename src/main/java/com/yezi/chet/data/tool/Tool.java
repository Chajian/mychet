package com.yezi.chet.data.tool;

import java.util.Random;

public class Tool {


    /**
     * 随机获得一个游客名
     * @return 返回一个游客名
     */
    public static String getTrandeName(){
        Random random = new Random();
        long code = System.currentTimeMillis()/random.nextInt(5892);
        return "游客"+code;
    }
}
