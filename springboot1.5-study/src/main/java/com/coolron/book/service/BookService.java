package com.coolron.book.service;

import com.coolron.book.domain.Book;
import com.coolron.book.domain.BookDetail;
import com.coolron.book.domain.Tag;
import com.coolron.common.utils.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @Auther: xf
 * @Date: 2018/7/28 14:59
 * @Description:
 */
public interface BookService {
    int addBook(Book book);

    int addBookTag(Tag tag);

    PageBean<Book> getBookList(Map<String, Object> page, Integer pageSize);

    BookDetail getDetail(Integer id);

    void updateCount(Book book);

    List<Book> getTop(Integer num);
}
