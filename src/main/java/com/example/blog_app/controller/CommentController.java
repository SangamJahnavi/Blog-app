package com.example.blog_app.controller;

import com.example.blog_app.payloads.ApiResponse;
import com.example.blog_app.payloads.CommentDto;
import com.example.blog_app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/{userId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId,@PathVariable  Integer userId
                                                    ){
        CommentDto commentDto1=this.commentService.createComment(commentDto,postId,userId);
//        System.out.println("comment for user"+commentDto1.);
        return new ResponseEntity<CommentDto>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.delComment(commentId);
        return ResponseEntity.ok(new ApiResponse("Comment del successfully",true));
    }
}
