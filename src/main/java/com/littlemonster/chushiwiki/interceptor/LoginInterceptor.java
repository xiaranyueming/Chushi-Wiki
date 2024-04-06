package com.littlemonster.chushiwiki.interceptor;

import com.littlemonster.chushiwiki.utils.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author: white-zhou
 * @date: 2024-04-06 21:16
 **/
@Slf4j

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       // 跨域请求
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(200);
            return true;
        }
        // 获取token
        String token = request.getHeader("Authorization");
        if (token == null) {
            response.setStatus(401);
            return false;
        }
        token = token.replace("Bearer ", "");

        // 解析token
        Map<String, Object> map = CommonUtils.parseToken(token);
        if (map.isEmpty()) {
            response.setStatus(401);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
