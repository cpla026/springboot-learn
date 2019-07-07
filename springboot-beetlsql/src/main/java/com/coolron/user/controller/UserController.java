package com.coolron.user.controller;

import com.coolron.user.domain.User;
import com.coolron.user.service.UserService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xf
 * @Date: 2018/12/2 21:52
 * @Description:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 使用 BaseMapper 查询
     * @param id
     * @return
     */
    @GetMapping(value = "/getUserInfo/{id}")
    public User getUserInfo(@PathVariable Integer id) {
        return userService.getUserInfo(id);
    }
    /**
     * 使用Query 查询器查询   单表操作建议使用
     * @param keyword
     * @return
     */
    @GetMapping(value = "/getUserListQuery")
    public List<User> getUserListQuery(@RequestParam(value = "keyword", required = false) String keyword) {
        return userService.getUserListQuery(keyword);
    }
    /**
     * 使用 sqlId 查询
     */
    @GetMapping(value = "/getUserListSqlId")
    public List<User> getUserListSqlId(@RequestParam(value = "keyword", required = false) String keyword) {
        return userService.getUserListSqlId(keyword);
    }
    @GetMapping(value = "/getUserListPageQuery")
    public PageQuery getUserListPageQuery(@RequestParam(value = "pageNumber", required = false,defaultValue = "1") int pageNumber,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                          @RequestParam(value = "name", required = false) String name) {

        // 构造不一定要这样 可参看PageQuery构造器
        PageQuery<User> query = new PageQuery(pageNumber, pageSize);
        //query.setPageSize(5);
        // 过滤条件
        Map<String,Object> paras = new HashMap<String,Object>(1);
        paras.put("name", name);
        query.setParas(paras);

        System.out.println(query.getTotalPage());
        System.out.println(query.getTotalRow());
        System.out.println(query.getPageNumber());
        List<User> list = query.getList();

        return userService.getUserListPageQuery(query);
    }
}
