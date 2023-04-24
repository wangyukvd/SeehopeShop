package com.seehope.servlet;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.seehope.entity.Pro;
import com.seehope.service.ProService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Com.seehope.servlet包下创建ShoppingServlet, URL为shopping,
//用于接收查询条件和分页信息，再调用业务层封装成PageInfo对象，再传有关数据
//(包括PageInfo对象和查询条件)给前台shopping.jsp进行分页展示。
//        Cid对应的品牌信息也在这里查询出来并封装。
@WebServlet(name = "ShoppingServlet", value = "/shopping")
public class ShoppingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageno = 1;     //默认页数
        int pageSize = 8;   //每页显示的信息数
        String changeIndex = request.getParameter("pi");
        if(changeIndex !=null){
            // 符合条件就把相应的页数赋给index
            pageno = Integer.parseInt(changeIndex);
        }

        //获取各属性的值  搜索框下面那些按钮的

        String cid =request.getParameter("cid");
        String pName =request.getParameter("pName");
        String pSn =request.getParameter("pSn");
        System.out.println("pSn:"+pSn);
        String minPrice =request.getParameter("minPrice");
        String maxPrice =request.getParameter("maxPrice");

        Map<String,String> map=new HashMap<>();
        map.put("cid",cid);
        map.put("pName",pName);
        map.put("pSn",pSn);
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        ProService proService=new ProService();
        PageInfo<Pro> pageInfo=proService.findPros(pageno,pageSize,map);

        //存储查询条件
        request.setAttribute("cid",cid);
        request.setAttribute("pName",pName);
        request.setAttribute("pSn",pSn);
        request.setAttribute("minPrice",minPrice);
        request.setAttribute("maxPrice",maxPrice);
        request.setAttribute("page",pageInfo);

        if(cid!=null&&!"".equals(cid)){
            List<String> pList=proService.findPname(Integer.parseInt(cid));
            request.setAttribute("pList",pList);//存储分页信息
        }
        request.getRequestDispatcher("shopping.jsp").forward(request,response);
    }
}
