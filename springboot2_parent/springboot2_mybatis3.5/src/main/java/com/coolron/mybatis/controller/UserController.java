package com.coolron.mybatis.controller;

import com.coolron.common.utils.ApiResult;
import com.coolron.mybatis.domain.User;
import com.coolron.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ApiResult getInfo(@PathVariable(value = "id") String id){
        User user = null;
        try {
            user = userService.getInfo(id);
            return ApiResult.ok(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.build(400, e.getMessage());
        }
    }
}
