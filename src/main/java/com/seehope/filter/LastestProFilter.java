package com.seehope.filter;

import com.seehope.entity.Pro;
import com.seehope.service.ProService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/index.jsp")
public class LastestProFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(req, resp);
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        Cookie[]cookies=request.getCookies();
        List<Pro> list1=new ArrayList<Pro>();
        ProService ps=new ProService();
        if(cookies !=null){
            for (Cookie cookie:cookies){
                if("proids".equals(cookie.getName())){
                    String proids =cookie.getValue();
                    String[] arr=proids.split("-");  //421-243-425  {421,423,425}
                    //List<String> asList =Arrays.asList(split);//转换成集合
                    for (int i=0;i< arr.length;i++){
                        int proid=Integer.parseInt(arr[i]);
                        Pro pro=ps.findId(proid);
                        list1.add(pro);
                        if(i>=5){
                            break;
                        }
                    }
                }
            }
        }
        request.setAttribute("list1",list1);
//        System.out.println(list1.get(0));
        chain.doFilter(request,response);
    }
}
