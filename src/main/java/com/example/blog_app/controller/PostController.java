package com.example.blog_app.controller;

import com.example.blog_app.payloads.Postdto;
import com.example.blog_app.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    public PostService postService;

    @PostMapping("/userId/{userId}/CategoryId/{categoryId}/posts")
    public ResponseEntity<Postdto> createPost(@RequestBody Postdto postdto,@PathVariable Integer userId, @PathVariable   Integer categoryId){
        Postdto post=this.postService.createPost(postdto,userId,categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/userId/{userId}/posts")
    public ResponseEntity<List<Postdto>> getPostbyUser(@PathVariable Integer userId){
        List<Postdto> postdtos =this.postService.getPostbyUser(userId);
        return ResponseEntity.ok(postdtos);
    }

    @GetMapping("/categoryId/{categoryId}/posts")
    public ResponseEntity<List<Postdto>> getPostbyCategory(@PathVariable Integer categoryId){
        List<Postdto> postdtos =this.postService.getPostsbyCategory(categoryId);
        return ResponseEntity.ok(postdtos);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Postdto> getPostbyId(@PathVariable Integer postId){
        Postdto postdto=this.postService.getPostById(postId);
        return ResponseEntity.ok(postdto);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Postdto>> getAllPosts(){
        List<Postdto> postdtos=this.postService.getAllPosts();
        return ResponseEntity.ok(postdtos);
    }


}
