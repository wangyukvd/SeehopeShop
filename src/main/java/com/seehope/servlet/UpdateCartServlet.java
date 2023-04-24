package com.seehope.servlet;

import com.seehope.entity.Pro;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UpdateCartServlet", value = "/updateCart")
public class UpdateCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =request.getParameter("proid");
        int proid=Integer.parseInt(id);
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        //注：只能重新id的hashcode
        Pro p =new Pro();
        p.setId(proid);
//      Pro p =proService.findId(proid);
        HttpSession session=request.getSession();
        Map<Pro,Integer>cart=(Map<Pro, Integer>) session.getAttribute("cart");
        //如果商品数据为0，就删除对象
        if(quantity == 0){
            cart.remove(p);
        }
        //判断如果找到与id相同的商品
        if(cart.containsKey(p)){
            cart.put(p,quantity); //更新数量
        }
        //把cart对象放回到session作用域中，更新数据
        session.setAttribute("cart",cart);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
