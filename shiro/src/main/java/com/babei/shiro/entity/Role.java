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
@TableName("role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @TableId
    private int id;
    private String name;
    //定义权限的集合
    @TableField(exist=false)
    private List<Perms> perms;

}
