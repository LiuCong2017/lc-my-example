package com.security.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.security.demo.entity.Role;
import com.security.demo.mapper.RoleMapper;
import com.security.demo.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public boolean save(Role entity) {
        List<Role> roles = roleMapper.selectList(new QueryWrapper<Role>().eq("name",entity.getName()));
        if (roles.size()>0){
            return false;
        }
        return super.save(entity);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleMapper.selectOne(new QueryWrapper<Role>().eq("name",roleName));
    }
}
