package com.example.blog_app.services;


import com.example.blog_app.entities.Category;
import com.example.blog_app.entities.User;
import com.example.blog_app.payloads.Postdto;

import java.util.List;

public interface PostService {

    public Postdto createPost(Postdto postdto,Integer userId,Integer categoryId);
    public Postdto updatePost(Postdto postdto,Integer postId);
    public void deletePost(Integer postId);
    public List<Postdto> getAllPosts();
    public Postdto getPostById(Integer postId);
    public  List<Postdto>  getPostsbyCategory(Integer categoryId);
    public List<Postdto> getPostbyUser(Integer userId);

}
