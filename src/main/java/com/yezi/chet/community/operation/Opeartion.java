package com.yezi.chet.community.operation;

import com.yezi.chet.data.ApplicationData;

public interface Opeartion {

    /**
     *
     * @param data 用户传递的数据
     * @return 返回一个Permission 判断此行为的类型和结果
     */
    int opeartion(ApplicationData data);

}
