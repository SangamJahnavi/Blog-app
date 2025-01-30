package com.example.blog_app.controller;

import com.example.blog_app.payloads.Postdto;
import com.example.blog_app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    public PostService postService;

    @PostMapping("/posts/userId/{userId}/CategoryId/{categoryId}")
    public ResponseEntity<Postdto> createPost(@RequestBody Postdto postdto,@PathVariable Integer userId, @PathVariable   Integer categoryId){
        Postdto post=this.postService.createPost(postdto,userId,categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
