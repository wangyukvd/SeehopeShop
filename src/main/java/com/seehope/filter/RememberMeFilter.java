package com.seehope.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "RememberMeFilter",urlPatterns = "/login.jsp" ,dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD})
public class RememberMeFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)resp;


        String username = "";
        String checked = "";

        // 得到客户端保存的Cookie数据
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (int i=0;i<cookies.length;i++){
                if("username".equals(cookies[i].getName())){
                    username =cookies[i].getValue();  //"tom"
                    checked="checked='checked'";
                }
            }
        }
        request.setAttribute("username",username);
        request.setAttribute("checked",checked);

        chain.doFilter(request, response);
    }
}
