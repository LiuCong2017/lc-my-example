package com.lc.aop.controller;

import com.lc.aop.service.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/19 14:30]
 */
@RestController
public class DramaController {
    @Autowired
    Performance performance;

    @GetMapping("/perform")
    public void Perform(){
        performance.perform();
    }
}
