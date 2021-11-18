package com.babei.shiro.mapper;

import com.babei.shiro.entity.Apple;
import com.babei.shiro.entity.Perms;
import com.babei.shiro.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author ba'bei
 *@Date 2021/10/19
 **/
@Repository
public interface AppleMapper extends BaseMapper<Apple> {


}
