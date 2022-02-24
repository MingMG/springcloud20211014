package com.babei.shiro.service;

import com.babei.shiro.entity.Menu;
import com.babei.shiro.vo.MenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<MenuVO> listShopArea(String name);
}
