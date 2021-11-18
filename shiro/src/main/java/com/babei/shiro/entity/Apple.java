package com.babei.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@Author ba'bei
 *@Date 2021/11/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple {

    private int id;

    //颜色
    private String color;

    //重量
    private int weight;

    //产地
    private String origin;
}
