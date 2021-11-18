package com.babei.shiro.service;

import com.babei.shiro.entity.Perms;
import com.babei.shiro.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService extends IService<User> {
    void register (User user);

    User findByUsername(@Param("userName") String userName);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(@Param("id") int id);
}
