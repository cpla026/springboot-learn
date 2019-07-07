package com.coolron.common.config;

import com.coolron.common.utils.HaoCangResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理 Handler
 * 全局异常配置只需要添加一个全局的类即可
 * @ControllerAdvice 注解是用来配置控制器通知的，
 * 我们可以配置过滤拦截具体一种或者多种类型的注解，添加annotations属性即可
 */
//@ControllerAdvice(annotations = {RestController.class, Controller.class})
// 全局返回的都是Json格式的字符串，所以需要再类上配置@ResponseBody注解
//@ResponseBody
    //使用此切片后@Valid注解验证的参数后不用再加Errors或Bindingesult
@RestControllerAdvice(annotations = {RestController.class})
@Slf4j
public class RestExceptionHandler {
    /**
     * 默认统一异常处理方法
     * @ExceptionHandler 注解用来配置需要拦截的异常类型，默认是全局类型
     * @ResponseStatus 注解用于配置遇到该异常后返回数据时的StatusCode的值，我们这里默认使用值500
     * @ResponseStatus 是标记一个方法或异常类在返回时响应的http状态
     */
    @ExceptionHandler(Exception.class)
    //@ResponseStatus(reason = "exception",value = HttpStatus.BAD_REQUEST)
    public Object runtimeExceptionHandler(Exception e) {
       // return ApiResultGenerator.errorResult(e.getMessage(),e);
        e.printStackTrace();
        return HaoCangResult.build(400, e.getMessage());
    }


    /**
     *  拦截Exception类的异常
     * @return
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Map< String , Object > exceptionHandler(Exception e){
//
//        Map< String , Object > result = new HashMap< String , Object>();
//        result.put( "respCode"  ,  "9999" );
//        result.put("respMsg",e.getMessage());
//
//        //正常开发中，可创建一个统一响应实体，如CommonResp
//        return result;
//    }

    //@ExceptionHandler(MethodArgumentNotValidException.class)
    //public HaoCangResult handleBindException(MethodArgumentNotValidException ex) {
    //
    //    FieldError fieldError = ex.getBindingResult().getFieldError();
    //    log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
    //    return HaoCangResult.build(400, fieldError.getDefaultMessage());
    //}
    //
    //@ExceptionHandler(BindException.class)
    //public HaoCangResult handleBindException(BindException ex) {
    //
    //    //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
    //    FieldError fieldError = ex.getBindingResult().getFieldError();
    //    log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
    //    return HaoCangResult.build(400, fieldError.getDefaultMessage());
    //}

}
