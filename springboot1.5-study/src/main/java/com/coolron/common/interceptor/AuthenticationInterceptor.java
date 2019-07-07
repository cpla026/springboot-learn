package com.coolron.common.interceptor;

import com.coolron.common.validator.RequiredPermission;
import com.coolron.ron.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @Auther: xf
 * @Date: 2018/11/11 10:03
 * @Description: 权限拦截器
 */
// 自定义一个权限拦截器, 继承HandlerInterceptorAdapter类
//@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

        @Autowired
        private UserService userService;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            if (null == userService) {
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                userService = (UserService) factory.getBean("userService");
            }

            // 验证权限
            if (this.hasPermission(handler)) {
                return true;
            }
            //  null == request.getHeader("x-requested-with") TODO 暂时用这个来判断是否为ajax请求
            // 如果没有权限 则抛403异常 springboot会处理，跳转到 /error/403 页面
            response.sendError(HttpStatus.FORBIDDEN.value(), "无权限");
            return false;
        }

        /**
         * 是否有权限
         *
         * @param handler
         * @return
         */
        private boolean hasPermission(Object handler) {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                // 获取方法上的注解
                RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
                // 如果方法上的注解为空 则获取类的注解
                if (requiredPermission == null) {
                    requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
                }

                // 如果注解为null, 说明不需要拦截, 直接放过
                if (requiredPermission == null) {
                    return true;
                }

                // 如果标记了注解，则判断权限
                if (StringUtils.isNotBlank(requiredPermission.value())) {
                    // redis或数据库 中获取该用户的权限信息 并判断是否有权限
                    Set<String> permissionSet = userService.getPermissionSet();
                    if (CollectionUtils.isEmpty(permissionSet) ){
                        return false;
                    }
                    return permissionSet.contains(requiredPermission.value());
                }
            }
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            // TODO
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            // TODO
        }
    }
