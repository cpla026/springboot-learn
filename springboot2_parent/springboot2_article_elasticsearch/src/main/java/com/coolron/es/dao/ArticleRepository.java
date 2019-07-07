package com.coolron.es.dao;

import com.coolron.es.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Auther: xf
 * @Date: 2019/1/13 14:08
 * @Description:
 * 继承 ElasticsearchRepository<实体类, 实体类主键类型>
 */
public interface ArticleRepository extends ElasticsearchRepository<Article,Long> {

    // Pageable为分页使用，不需要分页是去除。
    Page<Article> findByName(String name, Pageable pageable);
    Page<Article> findByNameLike(String name, Pageable pageable);

    /**
     * 参考 官网 https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/
     * 仅仅作为理解，与本项目无关
     */
    /* 以Name 开头 */
/*    List<Book> findByNameStartingWith(String name);
    *//* 以Name 结尾 *//*
    List<Book> findByNameEndingWith(String name);
    *//* 包含 Name *//*
    List<Book> findByNameContaining(String name);
    *//* 年龄在 ageFrom 到 ageTo 之间 *//*
    List<Book> findByAmountBetween(Integer amountFrom, Integer amountTo);
    *//* 名称为name 和 价格为 price 的 *//*
    List<Book> findByNameAndPrice(String name, Integer price);
    *//* 名称为name 和 价格为 price 的 按照价格排序 *//*
    List<Book> findByNameAndPriceOrderByPriceDesc(String name, Integer price);
   */
}
