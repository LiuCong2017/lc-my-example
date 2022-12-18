package com.security.demo.mapper;

import com.security.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kavin
 * @since 2022-12-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
