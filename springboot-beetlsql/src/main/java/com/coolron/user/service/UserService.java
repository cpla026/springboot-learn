package com.coolron.user.service;

import com.coolron.user.domain.User;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/9/30 10:17
 * @Description:
 */
public interface UserService {

    User getUserInfo(Integer id);

    List<User> getUserListQuery(String keyword);

    List<User> getUserListSqlId(String keyword);

    PageQuery getUserListPageQuery(PageQuery query);
}
