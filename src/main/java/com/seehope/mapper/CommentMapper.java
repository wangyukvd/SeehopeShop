package com.seehope.mapper;

import com.seehope.entity.Comment;

import java.util.List;

public interface CommentMapper {

    //添加一条评论和查询某个商品的所有评论的方法

    //添加一条评论
    public int addComment(Comment comment);

    //查询针对某个商品的所以评论
    public List<Comment>searchComments(int proid);   //

}
