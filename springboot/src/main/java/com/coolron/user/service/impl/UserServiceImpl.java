package com.coolron.user.service.impl;/**
 * Created by Administrator on 2018/11/13.
 */

import com.coolron.user.dao.UserMapper;
import com.coolron.user.domain.User;
import com.coolron.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/10/03 15:16
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getAllUser() {
        return mapper.getAll();
    }

    @Override
    public int saveUser(User user) {
        return mapper.insertSelective(user);
    }

    @Override
    public User getUser(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUser(User user) {
        return mapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

}
