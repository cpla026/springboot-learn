package com.coolron.user.service.impl;

import com.coolron.user.dao.UserDao;
import com.coolron.user.domain.User;
import com.coolron.user.service.UserService;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/12/2 21:50
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    SQLManager sqlManager;
    /**
     * 使用 BaseMapper 查询
     */
    @Override
    public User getUserInfo(Integer id){
        return userDao.unique(id);
    }
    /**
     * Query查询器
     *  针对单表进行操作 建议使用
     */
    @Override
    public List<User> getUserListQuery(String keyword) {
//        Query<User> query = sqlManager.query(User.class);
//        List<User> list = query.andBetween("id", 1, 1640)
//                .andLike("name", "%t%")
//                .andIsNotNull("create_time")
//                .orderBy("id desc").select();
        Query<User> query = sqlManager.query(User.class);
        List<User> list = query.andLike("name", "%"+ keyword + "%")
                .orderBy("id desc").select();
        return list;
    }
    /**
     * 使用 sqlId 查询
     */
    @Override
    public List<User> getUserListSqlId(String keyword) {
        return userDao.selectUserByName(keyword);
    }
    /**
     * PageQuery
     */
    @Override
    public PageQuery getUserListPageQuery(PageQuery query) {
        userDao.getUserListPageQuery(query);
        return query;
    }
}
