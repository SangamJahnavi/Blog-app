package com.example.blog_app.services.impl;

import com.example.blog_app.entities.Category;
import com.example.blog_app.entities.Post;
import com.example.blog_app.entities.User;
import com.example.blog_app.exceptions.ResourceNotFoundException;
import com.example.blog_app.payloads.PostResponse;
import com.example.blog_app.payloads.Postdto;
import com.example.blog_app.repositories.CategoryRepo;
import com.example.blog_app.repositories.PostRepo;
import com.example.blog_app.repositories.UserRepo;
import com.example.blog_app.services.CategoryService;
import com.example.blog_app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        post.setPostTitle(postdto.getPostTitle());
        post.setContent(postdto.getContent());
        post.setImageName(postdto.getImageName());
        Post updatedPost=this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,Postdto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","id",postId));
        this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPosts(int pageSize, int pageNumber, String sortBy, String sortDir) {
//        List<Post> posts=this.postRepo.findAll();
//        List<Postdto> postdtos=posts.stream().map(post -> this.modelMapper.map(post,Postdto.class)).toList();
//        return postdtos;
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }
        else{
            sort=Sort.by(sortBy).descending();
        }

        Pageable p=PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePosts=this.postRepo.findAll(p);
        List<Post> posts=pagePosts.getContent();
        List<Postdto> postdtos=posts.stream().map(post -> this.modelMapper.map(post,Postdto.class)).toList();
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postdtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pageSize);
        postResponse.setTotalpages(pagePosts.getTotalPages());
        postResponse.setTotalelements(pagePosts.getNumberOfElements());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public Postdto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        Postdto postdto=this.modelMapper.map(post,Postdto.class);
        return postdto;
    }

    @Override
    public List<Postdto> getPostsbyCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        List<Post> posts=this.postRepo.findByCategory(category);
        List<Postdto> postdtos=posts.stream().map(post -> this.modelMapper.map(post,Postdto.class)).toList();
        return postdtos;
    }

    @Override
    public List<Postdto> getPostbyUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        List<Post> posts=this.postRepo.findByUser(user);
        List<Postdto> postdtos=posts.stream().map(post -> this.modelMapper.map(post,Postdto.class)).toList();
        return postdtos;
    }

    @Override
    public List<Postdto> searchPosts(String keyword) {
        List<Post> posts=this.postRepo.findByPostTitleContaining(keyword);
        List<Postdto> postdtos=posts.stream().map(post -> this.modelMapper.map(post,Postdto.class)).toList();
        return postdtos;
    }
}
