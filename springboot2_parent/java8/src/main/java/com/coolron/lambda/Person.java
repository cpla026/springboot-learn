package com.coolron.lambda;

import lombok.Data;

/**
 * @Auther: xf
 * @Date: 2019/1/10 10:11
 * @Description:
 */
@Data
public class Person {
    String firstName;
    String lastName;
    public Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
