package com.lc.dutychain.handler;

import com.lc.dutychain.entity.OrderContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 指定注入顺序为2
 *
 */
@Order(2)
@Component
public class ValidOrderHandleInterceptService implements OrderHandleIntercept {
    @Override
    public OrderContext handle(OrderContext context) {
        System.out.println("检查请求参数，是否合法，并且获取客户的银行账户");
        return context;
    }

}