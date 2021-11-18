package com.babei.shiro;


import cn.hutool.core.util.StrUtil;
import com.babei.shiro.entity.Menu;
import com.babei.shiro.utils.SaltUsils;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
class ShiroApplicationTests {

	@Test
	void contextLoads() {
		//模拟从数据库查询出来
		List<Menu> menus = Arrays.asList(
				new Menu(1,"根节点",0),
				new Menu(2,"子节点1",1),
				new Menu(3,"子节点1.1",2),
				new Menu(4,"子节点1.2",2),
				new Menu(5,"根节点1.3",2),
				new Menu(6,"根节点2",1),
				new Menu(7,"根节点2.1",6),
				new Menu(8,"根节点2.2",6),
				new Menu(9,"根节点2.2.1",7),
				new Menu(10,"根节点2.2.2",7),
				new Menu(11,"根节点3",1),
				new Menu(12,"根节点3.1",11),
				new Menu(13,"根节点2.2.1.1",9)
		);
		//获取父节点
		List<Menu> collect = menus.stream().filter(m->m.getParentId()==0).map(
				(m->{
					m.setChildList(getChildens(m,menus));
					return m;
				})
		).collect(Collectors.toList());
		System.out.println("-----------转JSON输出结果---------------");
		System.out.println(JSONArray.toJSONString(collect));
	}
	//递归查询子节点
	private List<Menu> getChildens(Menu root,List<Menu> all){
		List<Menu> children = all.stream().filter(m->{
			return Objects.equals(m.getParentId(),root.getId());
		}).map(
				m->{
					m.setChildList(getChildens(m,all));
					return m;
				}
		).collect(Collectors.toList());
		return children;
	}

}
