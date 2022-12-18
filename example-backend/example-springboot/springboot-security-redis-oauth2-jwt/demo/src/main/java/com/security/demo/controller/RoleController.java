package com.security.demo.controller;

import com.security.demo.entity.Role;
import com.security.demo.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "Role API")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @ApiOperation(value = "添加角色")
    @PostMapping("/addRole")
    public boolean addRole(@RequestBody Role role){
        return roleService.save(role);
    }

}
