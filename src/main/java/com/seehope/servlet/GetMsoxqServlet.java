package com.seehope.servlet;

import com.seehope.entity.Mso;
import com.seehope.service.MsoService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetMsoxqServlet", value = "/getMsoxq")
public class GetMsoxqServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetMsoxqServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msoid=request.getParameter("msoid");
        MsoService msoService=new MsoService();
        Mso mso=msoService.findMsoByMsoid(msoid);
        request.setAttribute("mso",mso);
        request.getRequestDispatcher("mso_detail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
