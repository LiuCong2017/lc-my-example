package com.lc.dutychain.handler;

import com.lc.dutychain.entity.OrderContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 指定注入顺序为1
 *
 */
@Order(1)
@Component
public class RepeatOrderHandleInterceptService implements OrderHandleIntercept {

    @Override
    public OrderContext handle(OrderContext context) {
        System.out.println("通过seqId，检查客户是否重复下单");
        return context;
    }

}