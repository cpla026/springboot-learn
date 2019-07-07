package com.coolron.ron.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{
    private static final long serialVersionUID = 6539921037742606422L;
    private Integer id;

    private Integer age;

    private String name;

    private String password;

    private String description;

    private Integer cityId;

}