package com.babei.shiro.service.impl;

import com.babei.shiro.entity.Perms;
import com.babei.shiro.entity.User;
import com.babei.shiro.mapper.UserMapper;
import com.babei.shiro.service.UserService;
import com.babei.shiro.utils.SaltUsils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@Author ba'bei
 *@Date 2021/10/19
 **/
@Service
@Transactional
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void register(User user) {
        //1.生成随机盐
        String salt = SaltUsils.getSalt(4);
        //2.将随机盐保存到数据库
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        baseMapper.insert(user);
    }

    @Override
    public User findByUsername(String userName) {
        return baseMapper.findByUsername(userName);
    }

    @Override
    public List<Perms> findPermsByRoleId(int id) {
        return baseMapper.findPermsByRoleId(id);
    }
}
