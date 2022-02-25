package com.babei.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ba'bei
 * @Date 2021/10/15
 **/
@RestController
@RequestMapping("/driver")
@Api(tags = "机车相关接口")
public class ProductController {

    @GetMapping("/hello")
    @ApiOperation(value = "访客模式下订单列表")
    public String hello() {
        return "hello word";
    }

    @GetMapping("/word")
    @ApiOperation(value = "访客模式下订单列表")
    public String word() {
        return "word";
    }

    @GetMapping("/word2")
    @ApiOperation(value = "访客模式下订单列表")
    public String word2() {
        return "word2";
    }
}
