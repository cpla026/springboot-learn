package com.coolron.pojo.user;
import com.google.common.collect.Lists;
import lombok.Data;
import java.util.List;
/**
 * @Auther: xf
 * @Date: 2018/10/03 16:58
 * @Description:
 */
@Data
public class User {
    private Integer id;
    private Integer age;
    private String name;
    private String password;
    private String description;

    private Integer cityId;

    private String gender;

    private List<String> hobby = Lists.newArrayList();
}