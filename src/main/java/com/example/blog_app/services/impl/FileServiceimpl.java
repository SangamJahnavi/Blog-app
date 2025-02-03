package com.example.blog_app.services.impl;

import com.example.blog_app.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceimpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

//        Get file name
        String filename=file.getOriginalFilename();

//        Define final image name
        String randomname= UUID.randomUUID().toString();
        randomname.concat(filename.substring(filename.lastIndexOf('.')));

        //        Get file path

        String filepath=path+ File.separator+randomname;


//        check if path exsts else create pathe
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

//        copy file

        Files.copy(file.getInputStream(), Paths.get(filepath));

        return  randomname;
    }
}
