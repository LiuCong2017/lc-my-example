package com.lc.dutychain;

import com.lc.dutychain.entity.OrderContext;
import com.lc.dutychain.service.OrderHandleChainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DutyChainApplicationTests {

    @Autowired
    private OrderHandleChainService orderHandleChainService;

    @Test
    public void test(){
        orderHandleChainService.execute(new OrderContext());
    }

}
