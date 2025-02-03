package com.example.blog_app.controller;

import com.example.blog_app.payloads.ApiResponse;
import com.example.blog_app.payloads.PostResponse;
import com.example.blog_app.payloads.Postdto;
import com.example.blog_app.services.FileService;
import com.example.blog_app.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.example.blog_app.config.Constants.*;

@RestController
@RequestMapping("/api")
public class PostController {


    @Autowired
    public PostService postService;

    @Autowired
    public FileService fileService;

    @Value("${project.image}")
    String path;

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
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam (value = "pageNumber",defaultValue = page_number,required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize",defaultValue = page_size,required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = sort_by, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir",defaultValue = sort_direction,required = false )String sortDir){

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

    @GetMapping("/posts/search/{searchKeyword}")
    public ResponseEntity<List<Postdto>> search(@PathVariable String searchKeyword){
        List<Postdto> postdtos =this.postService.searchPosts(searchKeyword);
        return ResponseEntity.ok(postdtos);
    }

//    File Services

//    To upload the post image
    @PostMapping("/posts/upload/images/{postId}")
    public ResponseEntity<Postdto> uploadImage(@RequestParam("image")MultipartFile image, @PathVariable Integer postId ) throws IOException {
        String uploadimage=this.fileService.uploadImage(path,image);
        Postdto postdto=this.postService.getPostById(postId);
        postdto.setImageName(uploadimage);
        Postdto updatedpost =this.postService.updatePost(postdto,postId);
        return ResponseEntity.ok(updatedpost);
    }

//    To serve the image
    @GetMapping("/posts/images/{imageName}")
    public void serve(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.serveImage(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
