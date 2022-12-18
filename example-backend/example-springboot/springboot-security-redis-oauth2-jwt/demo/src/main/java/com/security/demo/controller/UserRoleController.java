package com.security.demo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.security.demo.dto.UserRoleDTO;
import com.security.demo.entity.Role;
import com.security.demo.entity.User;
import com.security.demo.entity.UserRole;
import com.security.demo.service.IRoleService;
import com.security.demo.service.IUserRoleService;
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
@Api(tags = "UserRole API")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    IUserService userService;
    @Autowired
    IRoleService roleService;

    @ApiOperation(value = "添加用户角色")
    @PostMapping("/addUserRole")
    public boolean addUserRole(@RequestBody UserRoleDTO userRoleDTO){
        User user = userService.findUserByUserName(userRoleDTO.getUserName());
        Role role = roleService.findRoleByRoleName(userRoleDTO.getRoleName());
        if ((null!=user) && (null!=role)){
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            return userRoleService.save(userRole);
        }
        return false;
    }

}
