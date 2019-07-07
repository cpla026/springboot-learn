package com.coolron.service.impl.user;/**
 * Created by Administrator on 2018/11/13.
 */

import com.coolron.dao.user.UserMapper;
import com.coolron.pojo.user.User;
import com.coolron.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/10/03 15:16
 * @Description:
 */
@Service("userService")
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
