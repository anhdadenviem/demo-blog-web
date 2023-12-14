package com.example.blogpages.repository;

import com.example.blogpages.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findAllByUser_Id(Long id);
    List<Blog> findByUser_Id(Long id);
    List<Blog> findByTitleContaining(String tittle);
    List<Blog> findByMode(String mode);

}
