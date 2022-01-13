package com.lc.aop.service.impl;

import com.lc.aop.service.Performance;
import org.springframework.stereotype.Service;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/19 14:01]
 */
@Service
public class Drama implements Performance {

    @Override
    public void perform() {
        System.out.println("执行业务服务");
    }

}
