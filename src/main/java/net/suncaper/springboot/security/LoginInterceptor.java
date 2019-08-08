package net.suncaper.springboot.security;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       Object user_id=request.getSession().getAttribute("USER_ID");
        if(user_id!=null){
            return  true;
        }else {response.sendRedirect("/user/login");
            return false;
        }

    }
}
