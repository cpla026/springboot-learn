package com.coolron.controller;

import com.coolron.service.MyStarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xf
 * @Date: 2019/8/25 15:48
 * @Description:
 */
@RestController
public class CustomStarterTestController {

    @Autowired
    private MyStarterService myStarterService;

    @GetMapping("/heart")
    public String heart(){
        return myStarterService.heart();
    }

}
