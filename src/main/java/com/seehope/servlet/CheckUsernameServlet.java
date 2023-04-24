package com.seehope.servlet;

import com.seehope.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckUsernameServlet", value = "/checkUsername")
public class CheckUsernameServlet extends HttpServlet {

    //7.在src/com.seehope.servlet下创建CheckUsernameServlet，
    // 用于验证用户名是否存在。URL为/checkUsername,注意输出boolean类型，供前端的ajax的回调函数使用。
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获得用户名
        String username = request.getParameter("username");
        System.out.println("获取到用户名称（username）:"+username);
        //System.out.println(username);
        UserService service=new UserService();  //调用业务层的方法
        boolean isExist=false;    //用于检测用户名是否可以使用
        try {
            isExist = service.checkUsername(username);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("用户名是否可用（isExist）:"+isExist);
        //String json ="{\"isExist\":" + isExist + "}" ;
        response.getWriter().print(isExist);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
