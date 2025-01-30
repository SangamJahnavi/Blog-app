package com.example.blog_app.entities;


import jakarta.persistence.*;
import org.modelmapper.internal.bytebuddy.build.HashCodeAndEqualsPlugin;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "Posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String postTitle;

    private String imageName;

    private String content;

    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

}
