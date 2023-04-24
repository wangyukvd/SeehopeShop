package com.seehope.servlet;

import com.seehope.entity.Mso;
import com.seehope.entity.User;
import com.seehope.service.MsoService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindMsosByUserIdServlet", value = "/findMsosByUserId")
public class FindMsosByUserIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FindMsosByUserIdServlet(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session =request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }

        MsoService msoService=new MsoService();
        List<Mso> orderList=msoService.findMsosByUserId(user.getId());
        session.removeAttribute("mso");
        session.removeAttribute("amount");
        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("mso_list.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
