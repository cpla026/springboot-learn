package com.coolron.ron.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Auther: xf
 * @Date: 2018/9/29 13:58
 * @Description:
 */
@Data
public class DemoReq {


//    @NotEmpty :不能为null，且Size>0
//    @NotNull:不能为null，但可以为empty,没有Size的约束
//    @NotBlank:只用于String,不能为null且trim()之后size>0

//    @NotEmpty
//    带注解的String，collection，map或数组不能为null，也不能为空。
//    也就是说，有@NotEmpty注解的String、Collection、Map、数组是不能为null或长度为0

//    @NotBlank
//    验证注释的String不是null或空的，与@NotEmpty的区别在于，尾部空格被忽略，也就是说，纯空格的String也是不符合规则的，此注解只能用于验证String类型

//    @NotNull
//    注释的元素不能为null。接受任何类型

    @NotNull(message = "id 不能为空")
    private Long id;

    @Null(message = "code不能为空")
    private String code;

   // @Length (max  = 10, message = "name长度不能超过10")
    @NotNull(message = "name为空")
    private String name;
}
