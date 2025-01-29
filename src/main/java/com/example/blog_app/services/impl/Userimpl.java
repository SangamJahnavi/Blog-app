package com.example.blog_app.services.impl;

import com.example.blog_app.entities.*;
import com.example.blog_app.payloads.Userdto;
import com.example.blog_app.repositories.UserRepo;
import com.example.blog_app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.blog_app.exceptions.ResourceNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userimpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Userdto createUser(Userdto userdto) {
        User user=this.dtotouser(userdto);
        User saveduser=this.userRepo.save(user);
        return this.usertodto(saveduser);
    }

    @Override
    public Userdto updateuser(Userdto userdto, Integer userid) {
        User user=this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("name","id",userid));
        user.setName(userdto.getName());
        user.setAbout(userdto.getAbout());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        User updateduser=this.userRepo.save(user);
        return this.usertodto(updateduser);
    }

    @Override
    public Userdto getUserbyId(int userid) {
        User user=this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("user","id",userid));
        return this.usertodto(user);
    }

    @Override
    public List<Userdto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        List<Userdto> usersdtos = users.stream().map(this::usertodto).toList();
        return usersdtos;
    }

    @Override
    public void deleteUser(Integer userid) {
        User user=this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("resource","id",userid));
        this.userRepo.delete(user);
    }

    private User dtotouser(Userdto userdto){
//        User user=new User();
//        user.setId(userdto.getId());
//        user.setAbout(userdto.getAbout());
//        user.setEmail(userdto.getEmail());
//        user.setPassword(userdto.getPassword());
//        user.setName(userdto.getName());
        return modelMapper.map(userdto,User.class);
//        return user;
    }

    public Userdto usertodto(User user){
//        Userdto userdto=new Userdto();
//        userdto.setId(user.getId());
//        userdto.setAbout(user.getAbout());
//        userdto.setEmail(user.getEmail());
//        userdto.setPassword(user.getPassword());
//        userdto.setName(user.getName());
//        return userdto;
        return modelMapper.map(user,Userdto.class);
    }
}
