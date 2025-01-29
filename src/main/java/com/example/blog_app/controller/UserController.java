package com.example.blog_app.controller;

import com.example.blog_app.payloads.Userdto;
import com.example.blog_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

//    POST
    @PostMapping("/")
    public ResponseEntity<Userdto> createuser(@RequestBody Userdto userdto){
        Userdto userdto1=this.userService.createUser(userdto);
        return new ResponseEntity<>(userdto1, HttpStatus.CREATED);
    }
}
