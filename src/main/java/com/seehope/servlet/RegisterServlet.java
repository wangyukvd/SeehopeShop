package com.seehope.servlet;

import com.seehope.entity.User;
import com.seehope.service.UserService;
import com.seehope.util.Md5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    public  RegisterServlet(){super();}

    public void destroy() {
        super.destroy();
    }

    //9.在com.seehope.servlet包下创建RegisterServlet，URL为register用于注册：
    //要判断用户输入的验证码与手机发送的验证码。注册成功跳到go.jsp.否则跳到register.jsp,
    // 如果其他都填 好了，但验证码不对，应该设置数据回显。
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("测试注册");
        request.setCharacterEncoding("UTF-8");

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String telephone=request.getParameter("telephone");
        String number=request.getParameter("number");
//        System.out.println(number);
        String number1= (String) request.getSession().getAttribute("num");
        if(number1==null){
            request.setAttribute("msg","验证码超时！");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }
        //判断验证码是否正确
        if (!number1.equals(number)){
            request.setAttribute("msg","验证码错误！");
            request.getRequestDispatcher("register.jsp").forward(request,response);
            return;
        }
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        request.setAttribute("telephone",telephone);//数据回显

        User user=new User();
        user.setUsername(username);
        user.setPassword(Md5Utils.getMD5(password));   //密码加密
        user.setTelephope(telephone);

        //将User传递给service层
        UserService service = new UserService();
        //调用工具类，发送

        boolean isRegisterSuccess=service.register(user);

        if(isRegisterSuccess){
            request.getRequestDispatcher("go.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
