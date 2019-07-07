package com.coolron.ron.controller;

import com.coolron.common.utils.HaoCangResult;
import com.coolron.ron.domain.DemoReq;
import com.coolron.ron.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.coolron.com
 */
@Slf4j
@RestController
public class HelloController {

    @Value("${springboot.random-id}")
    private long id;

    @Autowired
    private CityService cityService;

    /**
     * 对时间的处理
     *
     * 或者在请求参数上指定
     * @RequestParam(value = "datadt") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date datadt
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ApiIgnore
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public HaoCangResult index(@RequestParam(value = "param1") String param1,
                               @RequestParam(value = "param2") Integer param2) {

        List<Map<String,Object>> list = cityService.getAll();
        //log.info("random-id:,{}",id);
        //return HaoCangResult.ok("Who is your little fox, and who is your rose?");
        return HaoCangResult.ok(list);
    }

    /**
     * 测试事物
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public HaoCangResult test(){

            int i = cityService.addTrans();
            return HaoCangResult.ok(i);
    }

    @PostMapping(value = "/valid")
    public HaoCangResult valid(@Valid DemoReq demoReq) {

            return HaoCangResult.ok(demoReq);
    }

}