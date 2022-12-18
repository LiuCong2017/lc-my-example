package com.security.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.security.demo.entity.User;
import com.security.demo.mapper.UserMapper;
import com.security.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kavin
 * @since 2022-12-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean save(User entity) {
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().eq("name",entity.getName()));
        if (userList.size()>0){
            return false;
        }
        return super.save(entity);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("name",userName));
    }
}
