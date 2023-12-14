package com.example.blogpages.repository;

import com.example.blogpages.model.Blog;
import com.example.blogpages.model.Likee;
import com.example.blogpages.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.Like;

import java.util.List;

public interface LikeeRepository extends JpaRepository<Likee, Long> {
    Long countByBlog(Blog blog);
    List<Likee> findByBlog(Blog blog);
    Likee findByBlogAndUser(Blog blog, User user);
}
