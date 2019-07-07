package com.coolron.book.dao;

import com.coolron.book.domain.Book;
import com.coolron.book.domain.BookDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    Integer insertSelective(Book record);

    BookDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> getBookList(@Param("page") Map page);

    Integer findBookTotalCount(@Param("page") Map page);

    List<Book> getTop(Integer num);
}