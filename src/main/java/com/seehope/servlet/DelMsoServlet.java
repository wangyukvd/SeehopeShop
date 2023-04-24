package com.seehope.servlet;

import com.seehope.service.MsoService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DelMsoServlet", value = "/delMso")
public class DelMsoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String msoid = request.getParameter("msoid");
            MsoService msoService = new MsoService();
            msoService.delMso(msoid);
            response.getWriter().print(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
