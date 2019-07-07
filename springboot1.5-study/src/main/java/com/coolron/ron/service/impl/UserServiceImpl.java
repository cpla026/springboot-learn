package com.coolron.ron.service.impl;

import com.coolron.ron.dao.UserMapper;
import com.coolron.ron.domain.User;
import com.coolron.ron.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: xf
 * @Date: 2018/7/16 15:25
 * @Description:
 */
@Service("userService")
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<User> getUserListByCityId(Integer cityId) {
        return userMapper.getUserListByCityId(cityId);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public Set<String> getPermissionSet() {

        Set<String> set  = new HashSet<>();
        set.add("user:delete");
        set.add("user:view");
        return set;
    }
}
