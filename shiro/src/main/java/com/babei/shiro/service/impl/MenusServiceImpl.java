package com.babei.shiro.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.babei.shiro.entity.Menu;
import com.babei.shiro.mapper.MenuMapper;
import com.babei.shiro.service.MenuService;
import com.babei.shiro.vo.MenuVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ba'bei
 * @Date 2021/11/30
 **/
public class MenusServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<MenuVO> listShopArea(String name) {
        List<MenuVO> voList = new ArrayList<>();
      /*  List<Menu> list = list(Wrappers.<Menu>lambdaQuery()
                .like(StrUtil.isNotBlank(name), Menu::getName, name));*/
        List<Menu> list = list(Wrappers.emptyWrapper());
        List<Menu> collect = list.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());

        for (Menu menu : collect) {
            MenuVO build = MenuVO.builder()
                    .id(menu.getId())
                    .name(menu.getName())
                    .parentId(menu.parentId)
                    .build();
            voList.add(build);
            digui(build,list);
        }
        return voList;
    }


    public void digui(MenuVO parent, List<Menu> shopAreaList){

        List<Menu> children = shopAreaList.stream().filter(item -> item.getParentId().equals(parent.getId())).collect(Collectors.toList());
        parent.setChildList(CollectionUtil.newArrayList());

        if (CollectionUtil.isNotEmpty(children)){
            for (Menu child:children){
                MenuVO childVO = MenuVO.builder()
                        .id(child.id)
                        .name(child.name)
                        .parentId(child.parentId)
                        .build();
                parent.getChildList().add(childVO);
                this.digui(childVO,shopAreaList);
            }
        }

    }
}
