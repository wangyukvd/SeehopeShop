package com.seehope.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//4.登录所需的图片验证码的Servlet直接提供，名为GetCaptcha。由于用户可能由首页，商品列表页，商品详情页，购物车这几个位置进行登录，为了让用户登录成功后从哪里来回里去，需要记住用户登录前的URL,由过滤器实现.
//        从哪里登录，登录后回到哪里的实现技术:
//        技术原理，分析一下，只有index.jsp ,商品列表，商品详情，以及购物车4个地方有登录，要记住登录前的地址，
//        就必须用session来存储请求地址，将来在登录的LoginServlet中再取出，然后传递给登录的ajax回调函数，在回调函数中重定向即可
//        创建一个过滤器LoginPathFilter来要记住登录前的地址，只过滤index.jsp ,商品列表，商品详情，
//        以及购物车4个地方，用request.getRequestURI获取他们的请求地址。由于商品列表，商品详情还可能会带一堆参数，
//        这些参数也要考虑，否则不能准确的到回去。在包com.seehope.filter下创建过滤器，参考代码：
@WebFilter(filterName = "LoginPathFilter")
public class LoginPathFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
//      req.getSession().setAttribute("path","/SeehopeShop/index.jsp");
        String path=req.getRequestURI();//获得请求路径
        String params=req.getQueryString();//获得请求参数
        path=path+"?"+params;//拼接成完整路径

        req.getSession().setAttribute("path",path);
        System.out.println(path);
        System.out.println("session(path):"+(String) req.getSession().getAttribute("path"));//测试用
        chain.doFilter(req,resp);
    }
}
