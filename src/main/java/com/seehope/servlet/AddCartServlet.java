package com.seehope.servlet;

import com.seehope.entity.Pro;
import com.seehope.service.ProService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddCartServlet", value = "/addCart")
public class AddCartServlet extends HttpServlet {

    public AddCartServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("proid");  //421   ,423
        int proid=Integer.parseInt(id);//421,423
        ProService ps = new ProService();
        Pro p = ps.findId(proid);  //鲜橙粉,阿萨姆奶茶

        if(p.getpNum()==0){  //没有库存

            out.print("false");
            return;
        }
        //这是使用session实现购物车
        //从session中的购物车取出 来
        HttpSession session =request.getSession();
        //haspMap的键用于存储Pro对象,值用于存储商品的购买数量，
        //注意Pro实体类重写hashCode和equals方法（只选择id属性）;
        //注意Pro实体类重写hashCode和equals方法（只选择id属性）;
        //注意Pro实体类重写hashCode和equals方法（只选择id属性）;
        Map<Pro,Integer>cart=(Map<Pro, Integer>) session.getAttribute("cart"); //null,有
        //注意Book实体类要重写hashCode和equals方法(只选择id属性)
        int num = 1;
        //如何是第一次访问,没有购物车(haspMap)对象,我们就创建 一个购物车对象
        if(cart==null){
            cart = new HashMap<Pro,Integer>();  //新建了一个haspMsp
        }

        //查看当前集合中是否存在编号为id的商品,如果有就把值取出来加1;
        if(cart.containsKey(p)){
            if(cart.get(p)==p.getpNum()){ //原购物车中的数据已经等于库存了，不能再添加了
                out.print("false");
                return;
            }
            num=cart.get(p)+1; //num=1+1=2
        }
        //商品放入购物车
        cart.put(p,num);//(鲜橙粉,1)存入cart这个haspMap，（阿萨姆奶茶，1）存入cart这个haspMsp，（阿萨姆奶茶，2）存入cart这个haspMap

        //把cart对象放回session作用域中，更新数据
        session.setAttribute("cart",cart);  //把cart这个haspMap,存入session对象
        out.print("true");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
