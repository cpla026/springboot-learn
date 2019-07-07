package com.coolron.ron.dao;

import com.coolron.ron.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> getAllUser();

    List<User> getUserListByCityId(Integer cityId);

    User getUserByUsername(String username);

    @Select("select * from user")
    List<Map<String,Object>> getAll();
}