package com.security.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.security.demo.entity.UserRole;
import com.security.demo.mapper.UserRoleMapper;
import com.security.demo.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public boolean save(UserRole entity) {
        List<UserRole> userRoleList = userRoleMapper.selectList(new QueryWrapper<UserRole>()
                .eq("user_id",entity.getUserId())
                .eq("role_id",entity.getRoleId())
        );
        if (userRoleList.size()>0){
            return false;
        }
        return super.save(entity);
    }
}
