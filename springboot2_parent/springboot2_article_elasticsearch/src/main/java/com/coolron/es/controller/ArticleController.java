package com.coolron.es.controller;

import com.coolron.common.utils.ApiResult;
import com.coolron.es.domain.Article;
import com.coolron.es.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: xf
 * @Date: 2019/1/13 14:06
 * @Description:
 */
@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("save")
    public Object save(@RequestBody Article article){
        return articleService.save(article);
    }

    @GetMapping(value = "article/query")
    public ApiResult query(@RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
                           @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pageSize,
                           @RequestParam(value = "searchContent") String searchContent){

        Page<Article> query = articleService.query(pageNumber, pageSize, searchContent);
        return ApiResult.ok(query.getContent());
    }

}
