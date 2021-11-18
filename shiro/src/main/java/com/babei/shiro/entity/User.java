package com.babei.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

/**
 *@Author ba'bei
 *@Date 2021/10/19
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  {
    @TableId
    private int id;
    private String username;
    private String password;
    private String salt;

    //定义角色集合
    @TableField(exist=false)
    private List<Role> roles;
}
