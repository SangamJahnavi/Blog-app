package com.example.blog_app.services;

import com.example.blog_app.payloads.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
    public  void delComment(Integer commentId);

}
