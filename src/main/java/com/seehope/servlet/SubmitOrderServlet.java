package com.seehope.servlet;

import com.seehope.entity.Mso;
import com.seehope.entity.Msoxq;
import com.seehope.entity.Pro;
import com.seehope.entity.User;
import com.seehope.service.MsoService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "SubmitOrderServlet", value = "/submitOrder")
public class SubmitOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SubmitOrderServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        // 封装Mso对象传递servic
        Mso mso = new Mso();
        //订单号
        String msg ="";
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMddHHmmssSSS");
        msg += sdf.format(date);
        mso.setMsoid(msg);
        //下单时间
        Date date1= new Date();
        mso.setMsoTime(date1);
        //获得购物车
//        Cart cart=(Cart) session.getAttribute("amount");
        double amount= (double) session.getAttribute("amount");
//        double total = cart.getTotal();
        mso.setMsomoney(amount);
        //支付状态
        String sta="未付款";
        mso.setPaystate(sta);
        //收货人
        String msoname=request.getParameter("msoname");
        //String msoname = new String(request.getParameter("msoname").getBytes("iso8859-1"),"utf-8");
        mso.setMsoname(msoname);
        //收货地址
        String address =request.getParameter("address");
        //String address = new String(request.getParameter("address").getBytes("iso8859-1"),"utf-8");
        mso.setAddress(address);
        //收货人电话
        String telephone= request.getParameter("telephone");
        mso.setTelephone(telephone);

        User user = (User) session.getAttribute("user");
        if(user !=null){
            mso.setUserid(user.getId());
            System.out.println(user.getId());
        }
        //获取订购数据
        Map<Pro,Integer>orderCart=(Map<Pro, Integer>) session.getAttribute("orderCart");

        //遍历，创建订单明细项，一对多关系，一个订单对应多个订单明细
        for (Map.Entry<Pro,Integer>entry:orderCart.entrySet()){

            Msoxq msoxq =new Msoxq();
            msoxq.setPro(entry.getKey());
            msoxq.setCount(entry.getValue());
            msoxq.setMso(mso);
            //将该订单添加到订单的订单项集合中
            mso.getMsoxqs().add(msoxq);
        }
        //提交到service
        MsoService service=new MsoService();
        service.submitMso(mso);//将订单和订单明细保存到数据库
        //默认成功，无法返回值

        //清空购物车（只清除选中进行结算的那部分）
        Map<Pro,Integer>cart=(Map<Pro, Integer>) session.getAttribute("cart");
        for (Pro pro:orderCart.keySet()){
            if(cart.containsKey(pro)){
                cart.remove(pro);
            }
        }

        //更新购物车
        session.setAttribute("cart",cart);
        session.removeAttribute("orderCart");
        session.setAttribute("mso",mso);
        //页面跳转，支付界面
        response.sendRedirect(request.getContextPath()+"/succ.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
