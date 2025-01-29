package com.example.blog_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CategoryId")
    private Integer Category_id;

    @Column(name = "CategoryName")
    private String Category_name;

    @Column(name = "CategoryDescription")
    private  String Category_description;


}
