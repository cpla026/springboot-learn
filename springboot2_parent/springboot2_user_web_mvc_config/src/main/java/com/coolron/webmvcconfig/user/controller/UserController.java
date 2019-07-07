package com.coolron.webmvcconfig.user.controller;

import com.coolron.common.utils.ApiResult;
import com.coolron.webmvcconfig.user.domain.User;
import com.coolron.webmvcconfig.user.domain.User_FastJsonTest;
import com.coolron.webmvcconfig.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/12/18 20:34
 * @Description:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/fastjsonList")
    public ApiResult fastjsonList(){
        List<User_FastJsonTest> list = userService.fastjsonList();
        return ApiResult.ok(list);
    }

    @GetMapping("/list")
    public ApiResult list(){
        List<User> list = userService.list();
        return ApiResult.ok(list);
    }

    @GetMapping("/{id}")
    public ApiResult getInfo(@PathVariable(value = "id") String id){
        User user = userService.getInfo(id);
        return ApiResult.ok(user);
    }
}
