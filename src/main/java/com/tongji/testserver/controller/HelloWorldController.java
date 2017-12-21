package com.tongji.testserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: demoserver
 * @description: test
 * @author: Annntn
 * @create: 2017-12-19 16:58
 **/

@RestController
@EnableSwagger2
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
