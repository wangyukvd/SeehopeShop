package com.seehope.servlet;

import com.seehope.entity.Comment;
import com.seehope.entity.User;
import com.seehope.service.CommentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

//添加评论AddCommentServlet
@WebServlet(name = "AddCommentServlet", value = "/addComment")
public class AddCommentServlet extends HttpServlet {

    public AddCommentServlet(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();

        Comment comment=new Comment();
        comment.setComments(request.getParameter("container"));
        comment.setProid(Integer.parseInt(request.getParameter("proid")));
        comment.setScore(Integer.parseInt(request.getParameter("score")));

        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if(user!=null){
            comment.setUserid(user.getId());
        }else {
            comment.setUserid(0);
        }

        CommentService commentService=new CommentService();
        commentService.addComment(comment);

        request.getRequestDispatcher("proDetail?proid="+comment.getProid()).forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
