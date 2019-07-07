package com.coolron.user.service;

import com.coolron.user.dao.UserMapper;
import com.coolron.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/12/18 17:35
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> list() {
        List<User> list = userMapper.list();
        return list;
    }

    public User getInfo(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
