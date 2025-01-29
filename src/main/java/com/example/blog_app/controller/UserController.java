package com.example.blog_app.controller;

import com.example.blog_app.payloads.ApiResponse;
import com.example.blog_app.payloads.Userdto;
import com.example.blog_app.services.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

//    POST
    @PostMapping("/")
    public ResponseEntity<Userdto> createuser(@Valid @RequestBody Userdto userdto){
        Userdto userdto1=this.userService.createUser(userdto);
        return new ResponseEntity<>(userdto1, HttpStatus.CREATED);
    }

//    PUT
    @PutMapping("/{userId}")
    public ResponseEntity<Userdto> updateuser(@Valid @RequestBody Userdto userdto,@PathVariable Integer userId){
        Userdto updateduser=this.userService.updateuser(userdto,userId);
        return ResponseEntity.ok(updateduser);
    }

//    DELETE
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteuser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted succcessfully",true),HttpStatus.OK);
    }

//    GET ALL USERS
    @GetMapping("/")
    public ResponseEntity<List<Userdto>> getallusers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

//    GET USER BY ID
    @GetMapping("/{userId}")
    public ResponseEntity<Userdto> getuserbyid(@PathVariable Integer userId){
        Userdto user=this.userService.getUserbyId(userId);
        return ResponseEntity.ok(user);
    }


}
