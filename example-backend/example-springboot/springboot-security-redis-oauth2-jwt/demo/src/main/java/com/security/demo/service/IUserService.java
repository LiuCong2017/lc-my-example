package com.security.demo.service;

import com.security.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kavin
 * @since 2022-12-18
 */
public interface IUserService extends IService<User> {
    User findUserByUserName(String userName);
}
