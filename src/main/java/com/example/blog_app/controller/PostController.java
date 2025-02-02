package com.example.blog_app.controller;

import com.example.blog_app.payloads.ApiResponse;
import com.example.blog_app.payloads.PostResponse;
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
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam (value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
                                                    @RequestParam(value = "sortDir",defaultValue = "asc",required = false )String sortDir){

        PostResponse postdtos=this.postService.getAllPosts(pageSize,pageNumber,sortBy,sortDir);
        return ResponseEntity.ok(postdtos);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Postdto> updatePost(@RequestBody Postdto postdto,@PathVariable Integer postId){
        Postdto postdto1=this.postService.updatePost(postdto,postId);
        return ResponseEntity.ok(postdto1);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted susccessfully",true),HttpStatus.OK);
    }


}
