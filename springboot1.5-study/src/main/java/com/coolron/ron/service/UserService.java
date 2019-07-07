package com.coolron.ron.service;

import com.coolron.ron.domain.User;

import java.util.List;
import java.util.Set;

/**
 * @Auther: xf
 * @Date: 2018/7/16 15:24
 * @Description:
 */
public interface UserService {
    List<User> getAllUser();

    int addUser(User user);

    User getUser(Integer id);

    int updateUser(User user);

    int deleteUser(Integer id);

    List<User> getUserListByCityId(Integer cityId);

    int saveUser(User user);

    Set<String> getPermissionSet();
}
