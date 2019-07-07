package com.coolron.es.service;

import com.coolron.es.dao.ArticleRepository;
import com.coolron.es.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Auther: xf
 * @Date: 2019/1/13 14:56
 * @Description:
 */
@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Article save(Article article) {
        Article save = articleRepository.save(article);
        return save;
    }

    public Page<Article> query(int pageNumber, int pageSize, String searchContent) {
        // 分页
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        Page<Article> articles = articleRepository.findByNameLike(searchContent, pageRequest);
        //Page<Article> articles = articleRepository.findByName(searchContent, pageRequest);

        return articles;
    }
}
