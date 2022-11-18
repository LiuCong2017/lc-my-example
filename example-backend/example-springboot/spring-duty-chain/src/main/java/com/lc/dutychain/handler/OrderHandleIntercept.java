package com.lc.dutychain.handler;


import com.lc.dutychain.entity.OrderContext;

public interface OrderHandleIntercept {

    /**
     * 对参数进行处理
     * @param context
     * @return
     */
    OrderContext handle(OrderContext context);
}
