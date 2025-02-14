package com.example.blog_app.payloads;

import com.example.blog_app.entities.Category;
import com.example.blog_app.entities.Comment;
import com.example.blog_app.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class Postdto {

    private Integer postId;

    private String postTitle;

    private String imageName;

    private String content;

    private Date date;

    private CategoryDto category;

    private Userdto user;

    private Set<CommentDto> comments=new HashSet<>();
}
