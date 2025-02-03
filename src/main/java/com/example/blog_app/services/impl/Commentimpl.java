package com.example.blog_app.services.impl;

import com.example.blog_app.entities.Comment;
import com.example.blog_app.entities.Post;
import com.example.blog_app.entities.User;
import com.example.blog_app.exceptions.ResourceNotFoundException;
import com.example.blog_app.payloads.CommentDto;
import com.example.blog_app.repositories.CommentRepo;
import com.example.blog_app.repositories.PostRepo;
import com.example.blog_app.repositories.UserRepo;
import com.example.blog_app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Commentimpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId) {
        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        comment.setPost(post);
        comment.setUser(user);

        Comment savedcomment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedcomment,CommentDto.class);

    }

    @Override
    public void delComment(Integer commentId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));
        this.commentRepo.delete(comment);

    }
}
