package com.coolron.book.service;

import com.coolron.book.dao.BookMapper;
import com.coolron.book.dao.TagMapper;
import com.coolron.book.domain.Book;
import com.coolron.book.domain.BookDetail;
import com.coolron.book.domain.Tag;
import com.coolron.common.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: xf
 * @Date: 2018/7/28 14:59
 * @Description:
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int addBook(Book book) {
        return bookMapper.insertSelective(book);
    }

    @Override
    public int addBookTag(Tag tag) {
        return tagMapper.insertSelective(tag);
    }

    @Override
    public PageBean<Book> getBookList(Map<String, Object> page, Integer pageSize) {

        PageBean<Book> pageBean = new PageBean<Book>();
        // 当前条件下的总记录数
        Integer totalCount = bookMapper.findBookTotalCount(page);
        pageBean.setTotalCount(totalCount);

        // 总页数 总记录数除以每页记录数向上取整
        double tc = totalCount; // 将总记录数转换为double
        Double ceilTotal = Math.ceil(tc / pageSize); // 向上取整
        int totalPage = ceilTotal.intValue();
        pageBean.setTotalPage(totalPage);

        // 根据stationtypeid 测点类型id 查询出所有的站点及站点对应的测点信息
        List<Book> list = bookMapper.getBookList(page);
        pageBean.setPageList(list);
        return pageBean;

        //return bookMapper.getBookList();
    }

    @Override
    public BookDetail getDetail(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateCount(Book book) {
        bookMapper.updateByPrimaryKeySelective(book);
    }

    @Override
    public List<Book> getTop(Integer num) {
        return bookMapper.getTop(num);
    }
}
