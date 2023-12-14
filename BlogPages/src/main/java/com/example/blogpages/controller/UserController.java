package com.example.blogpages.controller;

import com.example.blogpages.model.Blog;
import com.example.blogpages.model.User;
import com.example.blogpages.repository.BlogRepository;
import com.example.blogpages.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user-management")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/users")
    public ResponseEntity findAll(){
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity login(@PathVariable Long id){
        return new ResponseEntity(userRepository.findById(id),HttpStatus.OK);
    }
    @PostMapping ("/users/register")
    public ResponseEntity register(@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity( HttpStatus.OK);
    }
    @PostMapping("/users/login")
    public ResponseEntity login(@RequestBody User user){
        List<User> users = userRepository.findByUserAndAndPassword(user.getUser(),user.getPassword());
        return new ResponseEntity(users,HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity editUser(@PathVariable Long id,@RequestBody User user){
        user.setId(id);
        userRepository.save(user);
        return new ResponseEntity( HttpStatus.OK);
    }
    @PostMapping("/users/admin")
    public ResponseEntity findAdmin(@RequestBody User user){
        List<User> users;
        if(user.getUser().equals("admin1") && user.getPassword().equals("admin")) {
            users = userRepository.findByUserAndAndPassword(user.getUser(), user.getPassword());
        }else {
            users = null;
        }
        return new ResponseEntity(users ,HttpStatus.OK);
    }
    @PostMapping("/users/admin/list-user")
    public ResponseEntity findAllUser(@RequestBody User user){
        List<User> users;
        if(user.getUser().equals("admin1") && user.getPassword().equals("admin")) {
            users = userRepository.findAll();
        }else {
            users = null;
        }
        return new ResponseEntity(users ,HttpStatus.OK);
    }
    @DeleteMapping("/users/admin/{id}")
    public ResponseEntity DeleteUser(@PathVariable Long id,@RequestBody User user){
        if(user.getUser().equals("admin1") && user.getPassword().equals("admin")) {
            userRepository.deleteById(id);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity Delete(@PathVariable Long id){
        userRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
