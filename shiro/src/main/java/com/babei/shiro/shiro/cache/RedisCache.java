package com.babei.shiro.shiro.cache;

import com.babei.shiro.utils.ApplicationContextUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * 自定义redis缓存的实现
 *@Author ba'bei
 *@Date 2021/10/21
 **/
public class RedisCache<k,v> implements Cache<k,v> ,Serializable {

    private String cacheName;

    public RedisCache(){}

    public RedisCache(String cacheName){
        this.cacheName=cacheName;
    }
    @Override
    public v get(k k) throws CacheException {
        System.out.println("get key:"+k);
        return (v) getRedisTemplate().opsForHash().get(this.cacheName,k.toString());
    }

    @Override
    public v put(k k, v v) throws CacheException {
        System.out.println("put key:"+k);
        System.out.println("put value:"+v);

        getRedisTemplate().opsForHash().put(this.cacheName,k.toString(),v);

        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        return (v) getRedisTemplate().opsForHash().delete(this.cacheName,k.toString());
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<k> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<v> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }

    private  RedisTemplate getRedisTemplate(){
        RedisTemplate redisTempalte = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTempalte.setKeySerializer(new StringRedisSerializer());
        redisTempalte.setHashKeySerializer(new StringRedisSerializer());
        return redisTempalte;
    }

    private void setSerializer(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }
}
