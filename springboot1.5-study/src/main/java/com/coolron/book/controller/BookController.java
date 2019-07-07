package com.coolron.book.controller;

import com.alibaba.fastjson.JSON;
import com.coolron.book.domain.Book;
import com.coolron.book.domain.BookDetail;
import com.coolron.book.domain.Tag;
import com.coolron.book.service.BookService;
import com.coolron.common.utils.HaoCangResult;
import com.coolron.common.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xf
 * @Date: 2018/7/28 11:57
 * @Description:
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

 /*   public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String,  Map<String, Object>> map = restTemplate.getForObject("https://api.douban.com/v2/book/isbn/9787010009247",  Map.class);
        Book book = new Book();
        Tag tag = new Tag();

        book.setRate( Float.valueOf(String.valueOf(map.get("rating").get("average"))));
        book.setTitle(String.valueOf(map.get("title")));
        book.setAuthor(String.valueOf( ((List)map.get("author")).get(0)));
        book.setImage(String.valueOf(map.get("image")));
        book.setAlt(String.valueOf(map.get("alt")));
        book.setPublisher(String.valueOf(map.get("publisher")));
        book.setSummary(String.valueOf(map.get("summary")));
        book.setPrice(String.valueOf(map.get("price")));

        List<Tag> list = JSON.parseArray(JSON.toJSONString(map.get("tags")), Tag.class);

        System.out.println(JSON.toJSONString(book));
        System.out.println(JSON.toJSONString(list));
        System.out.println();
    }*/

    @RequestMapping(value={"getTop"}, method= RequestMethod.GET)
    public HaoCangResult getTop(@RequestParam Integer num){
        List<Book> tops = bookService.getTop(num);
        return HaoCangResult.ok(tops);
    }

    /**
     * 获取图书详情
     * @param id
     * @return
     */
    @RequestMapping(value={"getDetail"}, method= RequestMethod.GET)
    public HaoCangResult getDetail(@RequestParam Integer id){
        BookDetail book = bookService.getDetail(id);
//        if(null != book){
//            // 更新访问次数
//            book.setCount(book.getCount() + 1);
//            bookService.updateCount(book);
//        }
        return HaoCangResult.ok(book);
    }

    @RequestMapping(value={"getBookList"}, method= RequestMethod.GET)
    public HaoCangResult getBookList(@RequestParam(value = "currentpage", required = false, defaultValue = "1") Integer currentPage,
                                     @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize) {

        Map<String, Object> page = new HashMap<String, Object>();
        page.put("begin", (currentPage - 1) * pageSize); 	// 查询起始值
        page.put("pageSize", pageSize); 					// 当前页记录数

        // 分页查询
        PageBean<Book> pageBean = bookService.getBookList(page,pageSize);
        pageBean.setCurrpage(currentPage); 	// 当前页
        pageBean.setPageSize(pageSize); 	// 每页记录数

        // List<Book> bookList = bookService.getBookList();
        return HaoCangResult.ok(pageBean);
    }

    @RequestMapping(value={"addBook"}, method= RequestMethod.POST)
    public HaoCangResult addBook(String isbn, String openid) {
        // get book information by isbn in douban
        // https://api.douban.com/v2/book/isbn/9787010009247
        RestTemplate restTemplate = new RestTemplate();
        Map<String,  Map<String, Object>> map = restTemplate.getForObject("https://api.douban.com/v2/book/isbn/"+isbn,  Map.class);
        Book book = new Book();

        book.setRate( Float.valueOf(String.valueOf(map.get("rating").get("average"))));
        book.setTitle(String.valueOf(map.get("title")));
        book.setAuthor(String.valueOf( ((List)map.get("author")).get(0)));
        book.setImage(String.valueOf(map.get("image")));
        book.setAlt(String.valueOf(map.get("alt")));
        book.setPublisher(String.valueOf(map.get("publisher")));
        book.setSummary(String.valueOf(map.get("summary")));
        book.setPrice(String.valueOf(map.get("price")));
        book.setIsbn(isbn);
        book.setOpenid(openid);

        int result = bookService.addBook(book);

        List<Tag> list = JSON.parseArray(JSON.toJSONString(map.get("tags")), Tag.class);
        for (Tag tag : list) {
            tag.setBookId(book.getId());
            bookService.addBookTag(tag);
        }

        System.out.println(JSON.toJSONString(book));
        System.out.println(JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(book.getId()));

        return HaoCangResult.ok(book);
    }
}
