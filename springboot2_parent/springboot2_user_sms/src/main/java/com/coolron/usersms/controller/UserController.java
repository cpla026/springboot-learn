package com.coolron.usersms.controller;

import com.coolron.common.utils.ApiResult;
import com.coolron.usersms.domain.User;
import com.coolron.usersms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取短信验证码
     * @param mobile
     */
    @RequestMapping(value="/sendsms/{mobile}",method= RequestMethod.POST)
    public ApiResult sendsms(@PathVariable String mobile ){
        userService.sendSms(mobile);
        return ApiResult.ok();
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
