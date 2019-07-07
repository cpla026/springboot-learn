package com.coolron.mybatis.dao;

import com.coolron.mybatis.domain.User;
import com.coolron.mybatis.domain.UserList;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

public interface UserMapper {

    // 使用 Optional 包装返回对象
    Optional<User> selectByPrimaryKeyPlus(String id);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from tb_user")
    List<User> list();

    UserList getUserListByStatus();
}
