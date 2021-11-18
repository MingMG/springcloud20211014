package com.babei.shiro;

import com.babei.shiro.entity.Apple;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author ba'bei
 * @Date 2021/11/12
 **/
@SpringBootTest
@Log4j2
public class StreamTest {
    private static List<Apple> appleStore = new ArrayList<>();

    static {
        appleStore.add(new Apple(1, "red", 500, "湖南"));
        appleStore.add(new Apple(2, "red", 400, "湖南"));
        appleStore.add(new Apple(6, "green", 300, "湖南"));
        appleStore.add(new Apple(5, "green", 200, "天津"));
        appleStore.add(new Apple(4, "green", 100, "天津"));
        appleStore.add(new Apple(3, "red", 100, "天津"));

    }

    //找出红色的苹果
    @Test
    public void test1(){
        //过滤 （颜色为红色和重量大于400的）
        List<Apple> red = appleStore.stream()
                .filter(item -> item.getColor().equals("red") || item.getWeight()>200)
               // .filter(item -> item.getWeight()>400)
//                .sorted()

                .collect(Collectors.toList());
        List<Integer> integerStream = red.stream().map(item -> item.getId()).collect(Collectors.toList());

        log.info("红色的苹果为："+red);
        log.info("巴贝"+integerStream);
  /*  // 1、测试对集合排序
        String[] array = {"《1超少年密码》", "《3我们的少年时代》", "《6天坑鹰猎》", "《2断.桥》", "《4重生之们》"};
        List<String> nameList = new ArrayList<>();
        Collections.addAll(nameList, array);
        log.info("nameList集合大小：{}", nameList.size());

        // 使用java8排序  倒叙结果 【这里是正序还是倒叙 取决于 s2.compareTo(s1)   如果写成 s1.compareTo(s2) 则为正序】
        Collections.sort(nameList, (s1, s2) -> s2.compareTo(s1));
        log.info("倒叙排序后的nameList集合：{}", nameList);*/

        List<Apple> collect1 = appleStore.stream().sorted(Comparator.comparing(Apple::getId)).collect(Collectors.toList());
        collect1.forEach(System.out::println);
        //使用stream流遍历集合 【forEach】
       // appleStore.forEach(System.out::println);

        List<Apple> nameEnList = new ArrayList<>();
 
        List<Integer> collect = appleStore.stream().map(item ->
                item.getId()).collect(Collectors.toList());
        log.info(collect);

    }

    @Test
    void test2(){
        //求出每个颜色的平均重量
        appleStore.stream().collect(
                //基于颜色分组
                Collectors.groupingBy(item -> item.getColor(),
                        //统计平均重量
                        Collectors.averagingInt( a -> a.getWeight())))
                //打印
                .forEach((k,v) -> System.out.println(k+":"+v));

        appleStore.stream().collect(Collectors.groupingBy(item ->item.getColor(),
                Collectors.summingInt(item ->item.getWeight())))
                .forEach((k,v) -> System.out.println("每个颜色的总重量是："+k+":"+v));
    }
}
