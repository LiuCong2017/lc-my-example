package com.security.demo.controller;

import com.security.demo.entity.User;
import com.security.demo.mapper.UserMapper;
import com.security.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kavin
 * @since 2022-12-18
 */
@Api(tags = "User API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user){
        return userService.save(user);
    }

}
