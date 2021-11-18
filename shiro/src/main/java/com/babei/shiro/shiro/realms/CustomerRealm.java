package com.babei.shiro.shiro.realms;

import com.babei.shiro.entity.Perms;
import com.babei.shiro.entity.User;
import com.babei.shiro.service.UserService;
import com.babei.shiro.utils.ApplicationContextUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义realm
 *
 * @Author ba'bei
 * @Date 2021/10/19
 **/
public class CustomerRealm extends AuthorizingRealm implements Serializable {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();

        //根据主身份信息获取角色信息和权限信息
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.findByUsername(primaryPrincipal);
        //授权角色信息
        if (!ObjectUtils.isEmpty(user)){
            SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
                user.getRoles().forEach(role -> {
                    simpleAuthenticationInfo.addRole(role.getName());
                    //权限信息
                    List<Perms> perms = userService.findPermsByRoleId(role.getId());
                    if (!CollectionUtils.isEmpty(perms)){
                        perms.forEach(perms1 -> {
                            simpleAuthenticationInfo.addStringPermission(perms1.getName());
                        });
                    }
                });
                return simpleAuthenticationInfo;
        }
      /*  if ("babei".equals(primaryPrincipal)){
            SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();

            simpleAuthenticationInfo.addRole("user");
            simpleAuthenticationInfo.addStringPermission("user:*:*");
            return simpleAuthenticationInfo;
        }*/
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("========================");
        String principal = (String) token.getPrincipal();
        //在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.getBaseMapper().selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, principal));

        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), new MyByteSource(user.getSalt()), this.getName());
        }
        return null;
    }
}
