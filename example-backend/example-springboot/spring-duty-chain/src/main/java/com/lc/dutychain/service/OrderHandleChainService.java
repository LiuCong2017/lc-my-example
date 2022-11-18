package com.lc.dutychain.service;

import com.lc.dutychain.entity.OrderContext;
import com.lc.dutychain.handler.OrderHandleIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderHandleChainService  {

    @Autowired
    private List<OrderHandleIntercept> handleList;

    /**
     * 执行处理
     * @param context
     * @return
     */
    public OrderContext execute(OrderContext context){
        for (OrderHandleIntercept handleIntercept : handleList) {
            context =handleIntercept.handle(context);
        }
        return context;
    }
}