package com.example.blog_app.repositories;

import com.example.blog_app.entities.Category;
import com.example.blog_app.entities.Post;
import com.example.blog_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    public List<Post> findByUser(User user);
    public List<Post> findByCategory(Category category);
    public List<Post> findByPostTitleContaining(String title);
}
