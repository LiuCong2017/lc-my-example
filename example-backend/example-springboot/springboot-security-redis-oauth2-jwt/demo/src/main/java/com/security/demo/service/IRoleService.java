package com.security.demo.service;

import com.security.demo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kavin
 * @since 2022-12-18
 */
public interface IRoleService extends IService<Role> {
    Role findRoleByRoleName(String roleName);
}
