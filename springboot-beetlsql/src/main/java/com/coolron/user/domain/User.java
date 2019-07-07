package com.coolron.user.domain;

import org.beetl.sql.core.annotatoin.Table;

/*
*
* gen by beetlsql 2018-10-13
*/
@Table(name="springbootdb.user")
public class User   {

    /*
    用户编号
    */
    private Integer id ;
    private Integer age ;
    private Integer cityid ;
    private String description ;
    private String name ;

    public User() {
    }

    /**
     * 用户编号
     *@return
     */
    public Integer getId(){
        return  id;
    }
    /**
     * 用户编号
     *@param  id
     */
    public void setId(Integer id ){
        this.id = id;
    }

    public Integer getAge(){
        return  age;
    }
    public void setAge(Integer age ){
        this.age = age;
    }

    public Integer getCityid(){
        return  cityid;
    }
    public void setCityid(Integer cityid ){
        this.cityid = cityid;
    }

    /**
     * 描述
     *@return
     */
    public String getDescription(){
        return  description;
    }
    /**
     * 描述
     *@param  description
     */
    public void setDescription(String description ){
        this.description = description;
    }

    /**
     * 用户名称
     *@return
     */
    public String getName(){
        return  name;
    }
    /**
     * 用户名称
     *@param  name
     */
    public void setName(String name ){
        this.name = name;
    }


}