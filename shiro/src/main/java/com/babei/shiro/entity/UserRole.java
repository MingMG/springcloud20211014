package com.babei.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 *@Author ba'bei
 *@Date 2021/10/19
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {
    @TableId
    private int id;
    private String userId;
    private String roleId;

}
