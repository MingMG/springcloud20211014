package controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author ba'bei
 *@Date 2021/10/15
 **/
@RestController
@RequestMapping("/driver")
@Api(tags = "机车相关接口")
public class ProductController {

    @GetMapping("/hello")
    public String hello(){
        return "hello word";
    }
}
