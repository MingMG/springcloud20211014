package com.babei.shiro.config.shiroConfig;

import com.babei.shiro.shiro.cache.RedisCacheManager;
import com.babei.shiro.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *@Author ba'bei
 *@Date 2021/10/19
 **/
@Configuration
public class ShiroConfig implements Serializable {
    //创建shiroFilter
    //负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置系统受限资源
        Map<String, String> map = new HashMap<String,String>();
        map.put("/**","authc");
        map.put("/user/login","anon");
        map.put("/user/register","anon");
        map.put("/register.jsp","anon");//anon 设置为公共资源 需要放行的资源放在下面
        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //配置系统公共资源
        return shiroFilterFactoryBean;
    }

    //创建web管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //创建自定义realm
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);

        //开启缓存管理
        customerRealm.setCacheManager(new RedisCacheManager());
        //开启全局缓存
        customerRealm.setCachingEnabled(true);
        //开启认证的缓存
        customerRealm.setAuthenticationCachingEnabled(true);
        //认证的缓存名
        customerRealm.setAuthenticationCacheName("authenticationCache");
        //开启授权的缓存
        customerRealm.setAuthorizationCachingEnabled(true);
        //授权的缓存名
        customerRealm.setAuthorizationCacheName("authorizationCache");

        return customerRealm;

    }
}
