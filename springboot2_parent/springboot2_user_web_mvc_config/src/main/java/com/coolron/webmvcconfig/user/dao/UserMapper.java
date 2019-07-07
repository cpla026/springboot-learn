package com.coolron.webmvcconfig.user.dao;

import com.coolron.webmvcconfig.user.domain.User;
import com.coolron.webmvcconfig.user.domain.User_FastJsonTest;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from tb_user")
    List<User> list();

    @Select("select * from tb_user")
    List<User_FastJsonTest> fastjsonList();
}