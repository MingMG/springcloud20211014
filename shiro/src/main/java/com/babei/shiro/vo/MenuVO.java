package com.babei.shiro.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *@Author ba'bei
 *@Date 2021/11/28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO implements Serializable {
    /**
     * id
     */
    public Integer id;
    /**
     * 名称
     */
    public String name;
    /**
     * 父id ，根节点为0
     */
    public Integer parentId;
    /**
     * 子节点信息
     */
    private List<MenuVO> childList;



}
