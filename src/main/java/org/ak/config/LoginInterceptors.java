package org.ak.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptors implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletRequest.setCharacterEncoding("UTF-8");
        StringBuffer requestURL = httpServletRequest.getRequestURL();
        Object user2 = httpServletRequest.getSession().getAttribute("user");
        System.out.println("requestURL:"+requestURL.toString());
            if (requestURL.toString().contains("my")&&!requestURL.toString().contains("my/login")&&!requestURL.toString().contains("my/dologin")) {
                Object user = httpServletRequest.getSession().getAttribute("user");
                System.out.println("user:"+user);
                if (user == null) {
                    System.out.println("ifuser:"+user);
                    httpServletResponse.sendRedirect("/my/login");
                    return false;
                } else {
                    System.out.println("elseuser:"+user);
                    return true;
                }
            }
            System.out.println("elseuser2:"+user2);
            return true;

    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
