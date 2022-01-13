package com.lc.annotation.controller;

import com.lc.annotation.annotation.PrintLog;
import com.lc.annotation.annotation.ResponseResult;
import com.lc.annotation.bean.Response;
import com.lc.annotation.bean.ResponseCode;
import com.lc.annotation.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/23 09:27]
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 在 Controller 中的方法上使用 @PrintLog 自定义注解即可；
     * 当某个方法上使用了 自定义注解，那么这个方法就相当于一个切点，
     * 那么就会对这个方法做环绕（方法执行前和方法执行后）增强处理；
     */
    @PrintLog
    @GetMapping("/findAllUser")
    public Response<List<User>> findAllUser() {
//        log.info("开始查询所有数据...");

        List<User> findAllUser = new ArrayList<>();
        findAllUser.add(new User("test1", 26));
        findAllUser.add(new User("test2", 28));

        // 返回响应进行包装
        Response response = new Response(findAllUser, ResponseCode.SUCCESS.code(), ResponseCode.SUCCESS.message());

//        log.info("response: {} \n", response.toString());
        return response;
    }

    // 自定义注解用在了方法上 (自定义注解+拦截器)
    @ResponseResult
    @GetMapping("/findAllUserByAnnotation")
    public List<User> findAllUserByAnnotation() {
        log.info("开始查询所有数据...");

        List<User> findAllUser = new ArrayList<>();
        findAllUser.add(new User("test1", 26));
        findAllUser.add(new User("test2", 28));

        log.info("使用 @ResponseResult 自定义注解进行响应的包装，使controller代码更加简洁");
        return findAllUser;
    }


}
