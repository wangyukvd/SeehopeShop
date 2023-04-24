package com.seehope.servlet;

import com.seehope.entity.Pro;
import com.seehope.entity.User;
import com.seehope.service.ProService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "OrderCartServlet", value = "/orderCart")
public class OrderCartServlet extends HttpServlet {

    private static final long serialVersionUID=1L;

    public OrderCartServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            session.setAttribute("path","/SeehopeShop/cart.jsp");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String ides=request.getParameter("ids");//1,3,5
        System.out.println(ides);
        String[] ids=ides.split(",");//{1,3,5}
        ProService ps= new ProService();

        //将购物车的内容，请选出选中的部分 不如购物车有这些编辑号的商品：1,2,3,4,5但只勾选了这三项1，3,5
        Map<Pro, Integer>cart=(Map<Pro, Integer>) session.getAttribute("cart");//1,2,3,4,5全部购物车的商品
        Map<Pro,Integer>orderCart = new HashMap<>();//可能只有1,3,5等商品，相当于购物车的子集
        if(cart!=null){
            for (String id:ids){//1,3,5   //进行遍历，创建购物车的子集
                Pro p=ps.findId(Integer.parseInt(id));
                orderCart.put(p,cart.get(p));
            }
        }
        session.setAttribute("orderCart",orderCart);
        double amount=Double.parseDouble(request.getParameter("amount"));//订单金额
        session.setAttribute("amount",amount);
        response.sendRedirect("mso_info.jsp");//跳转到订单页面

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
