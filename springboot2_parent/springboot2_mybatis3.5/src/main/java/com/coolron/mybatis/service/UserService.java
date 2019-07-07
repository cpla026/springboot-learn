package com.coolron.mybatis.service;

import com.coolron.mybatis.dao.UserMapper;
import com.coolron.mybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Auther: xf
 * @Date: 2018/12/18 17:35
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 使用 Mybatis 3.5 新特性 optional 判断返回值User 是否为空
     */
    public User getInfo(String id) throws Exception {
        // 之前写法
    /*  User user = userMapper.selectByPrimaryKey(id);
        if(null == user){
            throw new Exception("This user does not exit!");
        }
        return user;*/

        /*
         * MyBatis3.5 使用 Optional 的写法
         * 结果不为空
         */
        Optional<User> user = userMapper.selectByPrimaryKeyPlus(id);
        return user.orElseThrow(() -> new IllegalArgumentException("This user does not exit!"));
    }

}
