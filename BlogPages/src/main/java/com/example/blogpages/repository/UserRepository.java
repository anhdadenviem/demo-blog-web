package com.example.blogpages.repository;

import com.example.blogpages.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUserAndAndPassword(String user,String password);

}
