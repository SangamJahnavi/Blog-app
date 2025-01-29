package com.example.blog_app.services;

import com.example.blog_app.entities.User;
import com.example.blog_app.payloads.Userdto;

import java.util.List;

public interface UserService {

    Userdto createUser(Userdto user);
    Userdto  updateuser(Userdto user, Integer userid);
    Userdto getUserbyId(int userid);
    List<Userdto> getAllUsers();
    void deleteUser(Integer userid);
}
