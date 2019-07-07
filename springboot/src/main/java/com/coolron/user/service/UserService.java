package com.coolron.user.service;

import com.coolron.user.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    int saveUser(User user);

    User getUser(Integer id);

    int updateUser(User user);

    int deleteUser(Integer id);

}
