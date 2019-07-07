package com.coolron.ron.controller;

import com.coolron.common.config.NewUserNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理 测试
 */
@RestController
public class RestExceptionTestController {

    @RequestMapping(value = "/index/{number}")
    public String index(@PathVariable int number){

        if(number == 0){
            throw new NewUserNotFoundException("number 为 0");
        }else{
            number = number/0;
            System.out.print(number);

            System.out.println(20 / number);
            return "get index page successfully.";
        }
    }
}