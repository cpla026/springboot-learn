package com.coolron.ron.controller;

import com.alibaba.fastjson.JSON;
import com.coolron.common.utils.HaoCangResult;
import com.coolron.common.utils.weixinUtils;
import com.coolron.common.validator.RequiredPermission;
import com.coolron.ron.domain.User;
import com.coolron.ron.domain.WXToken;
import com.coolron.ron.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.coolron.com
 *
 */
//@RequiredPermission("user")
@RestController
@RequestMapping(value="/user")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {

    //private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @Value("${appid}")
    private String appid;

    @Value("${secret}")
    private String secret;

    @Value("${grant_type}")
    private String grantType;

    @Value("${url}")
    private String url;

    @Autowired
    private UserService userService;

    @RequestMapping(value={"getOpenId"}, method=RequestMethod.GET)
    public HaoCangResult getOpenId(String code) {

        //微信端登录code值
       String str = weixinUtils.getSessionKeyOropenid(code, appid, secret, grantType, url);

        WXToken wxToken = JSON.parseObject(str,WXToken.class);

        System.out.println(JSON.toJSONString(wxToken));

        return HaoCangResult.ok(wxToken);
    }

    // 根据城市的ID 获取用户
    @RequestMapping(value={"getUserListByCityId/{cityId}"}, method=RequestMethod.GET)
    public HaoCangResult getUserListByCityId(@PathVariable Integer cityId) {
        List<User> userList = userService.getUserListByCityId(cityId);
        return HaoCangResult.ok(userList);
    }

    @RequiredPermission("user:view")
    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={"getUserList"}, method=RequestMethod.GET)
    public HaoCangResult getUserList() {
        //List<User> r = new ArrayList<User>(users.values());

        List<User> userList = userService.getAllUser();

        return HaoCangResult.ok(userList);
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="addUser", method=RequestMethod.POST)
    public HaoCangResult addUser(@RequestBody User user) {
        //users.put(user.getId(), user);

        int result = userService.addUser(user);

        return HaoCangResult.ok(result);
    }

    @RequiredPermission("user:get")
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "getUser", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="getUser/{id}", method=RequestMethod.GET)
    public HaoCangResult getUser(@PathVariable Integer id) {

        User user = userService.getUser(id);

        return HaoCangResult.ok(user);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="updateUser/{id}", method=RequestMethod.PUT)
    public HaoCangResult putUser(@PathVariable Integer id, @RequestBody User user) {
//        User u = users.get(id);
//        u.setName(user.getName());
//        u.setAge(user.getAge());
//        users.put(id, u);

        int result = userService.updateUser(user);

        return HaoCangResult.ok(result);
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
    public HaoCangResult deleteUser(@PathVariable Integer id) {
       // users.remove(id);
        int result = userService.deleteUser(id);

        return HaoCangResult.ok(result);
    }

}