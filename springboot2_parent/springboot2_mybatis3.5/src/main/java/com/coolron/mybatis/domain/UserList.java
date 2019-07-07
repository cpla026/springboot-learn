package com.coolron.mybatis.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xf
 * @Date: 2019/3/18 10:45
 * @Description:
 */
@Data
public class UserList {

    private List<User> list1 = new ArrayList<>();
    private List<User> list2 = new ArrayList<>();

}
