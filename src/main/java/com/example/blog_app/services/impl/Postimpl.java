package com.example.blog_app.services.impl;

import com.example.blog_app.entities.Category;
import com.example.blog_app.entities.Post;
import com.example.blog_app.entities.User;
import com.example.blog_app.exceptions.ResourceNotFoundException;
import com.example.blog_app.payloads.Postdto;
import com.example.blog_app.repositories.CategoryRepo;
import com.example.blog_app.repositories.PostRepo;
import com.example.blog_app.repositories.UserRepo;
import com.example.blog_app.services.CategoryService;
import com.example.blog_app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Postimpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Postdto createPost(Postdto postdto,Integer userId,Integer categoryId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        Post post=this.modelMapper.map(postdto,Post.class);
        post.setDate(new Date());
        post.setImageName("default.png");
        post .setCategory(category);
        post.setUser(user);

        Post newPost=this.postRepo.save(post);

        return this.modelMapper.map(newPost,Postdto.class);
    }

    @Override
    public Postdto updatePost(Postdto postdto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Postdto> getAllPosts() {
        return List.of();
    }

    @Override
    public Postdto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Postdto> getPostsbyCategory(Category category) {
        return List.of();
    }

    @Override
    public List<Postdto> getPostbyUser(User user) {
        return List.of();
    }
}
