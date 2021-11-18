package com.babei.shiro.mapper;

import com.babei.shiro.entity.Perms;
import com.babei.shiro.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Author ba'bei
 *@Date 2021/10/19
 **/
@Mapper
public interface UserMapper  extends BaseMapper<User> {

    //根据用户名查询所有角色
    User findByUsername(@Param("userName") String userName);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(@Param("id") int id);
}
