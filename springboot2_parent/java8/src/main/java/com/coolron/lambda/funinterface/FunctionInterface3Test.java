package com.coolron.lambda.funinterface;

/**
 * @Auther: xf
 * @Date: 2019/1/22 14:41
 * @Description:
 */
public class FunctionInterface3Test {
    public static void main(String[] args) {

        //int i =  func3((e) -> {return e - 5;});
        // 一行代码可省略 return、{}
        int i =  func3((e) -> e - 5);
        System.out.println(i);
    }
    private static int func3(FunctionInterface3 functionInterface3){
        int x = 10;
        return functionInterface3.test(x);
    }
}
