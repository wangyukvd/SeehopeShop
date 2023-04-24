package com.seehope.filter;

import com.seehope.entity.User;
import com.seehope.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter",urlPatterns = "/login.jsp" ,dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD})
public class AutoLoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)resp;


        // 得到客户端保存的Cookie数据
        Cookie[] cookies2 = request.getCookies();
        if(cookies2 != null){
            for (int i=0;i<cookies2.length;i++){
                if("user".equals(cookies2[i].getName())){
                    String userStr = cookies2[i].getValue();
                    String[] users=userStr.split("-");
                    String uname=users[0];
                    String password=users[1];
                    UserService userService=new UserService();

                }
            }
        }
        chain.doFilter(request,response);
    }
}
