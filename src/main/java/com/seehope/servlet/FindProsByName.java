package com.seehope.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seehope.service.ProService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "FindProsByName", value = "/findProNames")
public class FindProsByName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        3.利用Jackson将数据库查到的集合转为JSON格式的字符串,输出给AJAX的回调函数
        String proname=request.getParameter("proname");
        ProService proService=new ProService();
        List prosList=proService.findpSn(proname);
        response.setContentType("application/json;charset=UTF-8");

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(prosList);

        /*Gson gson=new Gson();
        * String json=gson.toJson(prosList)*/
        System.out.println("商品"+json);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
