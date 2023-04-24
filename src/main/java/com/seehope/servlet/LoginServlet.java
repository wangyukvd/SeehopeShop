package com.seehope.servlet;

import com.seehope.entity.User;
import com.seehope.service.UserService;
import com.seehope.util.Md5Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    public LoginServlet(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isRedirect =true;
        HttpSession session=request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        //获得输入的用户名和密码
        Md5Utils md5=new Md5Utils();
        String username = request.getParameter("username");
        String password = md5.getMD5(request.getParameter("password"));

        //将用户名和密码传递给service层
        String validCode=request.getParameter("validCode");
        String vcode= (String) session.getAttribute("vcode");
        if(!validCode.equals(vcode)){
            response.getWriter().write("{\"vcode\":"+false+"}");
            return;
        }

        UserService service=new UserService();
        User user=null;

        user=service.login(username,password);

        //判断用户是否denture成功 user是否是null
        if(user!=null){
            // 登入成功
            //********************判断用户是否勾选了自动登录***********************

            String rememberMe=request.getParameter("rememberMe");
            if ("true".equals(rememberMe)){


                Cookie cookie_username =new Cookie("cookie_username",user.getUsername());
                cookie_username.setMaxAge(10*60);

                response.addCookie(cookie_username);

            }

            String autoLogin=request.getParameter("autoLogin");
            if ("true".equals(autoLogin)){

                //创建存储用户名与密码的cooki额，可用于记住用户名或者自动登录，版本暂时没有做自动登录和记住用户名。
                //下面代码备用，仅供参考
                Cookie cookie_username =new Cookie("cookie_username",user.getUsername());
                cookie_username.setMaxAge(10*60);
                //创建存储密码的cookie
                Cookie cookie_password =new Cookie("cookie_password",user.getPassword());
                cookie_password.setMaxAge(10*60);

                response.addCookie(cookie_username);
                response.addCookie(cookie_password);

            }

            //*********************************************************************

            //将user对象存到session中
            session.setAttribute("user",user);

            //原来从哪里登录返回哪里
            String path= (String) session.getAttribute("path");//获取登入前的URI


//            if(path!=null&path.equals("cart")){
//                path+=".jsp";
//            }


                if(path==null) {
                    path = "index.jsp";
                }
                //测试一下
                System.out.println("{\"isRedirect\":" + isRedirect +",\"path\":\""+path+"\"}");
                response.getWriter().write("{\"isRedirect\":" + isRedirect +",\"path\":\""+path+"\"}");
            }else {
                isRedirect =false; //表示错误
                response.getWriter().write("{\"isRedirect\":"+isRedirect+"}");
            }

        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }
}
