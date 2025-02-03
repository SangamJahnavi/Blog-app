package com.example.blog_app.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    public String uploadImage(String path, MultipartFile file) throws IOException;
//    public String serveImage(String path, String filename);

}
