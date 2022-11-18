package com.lc.dutychain.handler;

import com.lc.dutychain.entity.OrderContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 指定注入顺序为3
 *
 */
@Order(3)
@Component
public class BankOrderHandleInterceptService implements OrderHandleIntercept {

    @Override
    public OrderContext handle(OrderContext context) {
        System.out.println("检查银行账户是否合法，调用银行系统检查银行账户余额是否满足下单金额");
        return context;
    }

}
