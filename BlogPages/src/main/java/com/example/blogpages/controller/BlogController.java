package com.example.blogpages.controller;

import com.example.blogpages.model.Blog;
import com.example.blogpages.model.User;
import com.example.blogpages.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user-management/users/blog-management")
public class BlogController {
    @Autowired
    BlogRepository blogRepository;
    @GetMapping("/blogs")
    public ResponseEntity findAll(){
        return new ResponseEntity(blogRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/blogs/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity(blogRepository.findById(id), HttpStatus.OK);
    }
    @PostMapping ("/blogs")
    public ResponseEntity addBlog(@RequestBody Blog blog){
        blogRepository.save(blog);
        return new ResponseEntity( HttpStatus.OK);
    }
    @PutMapping ("/blogs/{id}")
    public ResponseEntity editBlog(@PathVariable Long id,@RequestBody Blog blog){
        blog.setId(id);
        blogRepository.save(blog);
        return new ResponseEntity( HttpStatus.OK);
    }
    @DeleteMapping ("/blogs/{id}")
    public ResponseEntity deleteBlog(@PathVariable Long id){
        blogRepository.deleteById(id);
        return new ResponseEntity( HttpStatus.OK);
    }
    @GetMapping("/blogs/user/{id_user}")
    public ResponseEntity findAllByUser(@PathVariable Long id_user){
        return new ResponseEntity(blogRepository.findAllByUser_Id(id_user), HttpStatus.OK);
    }
    @GetMapping("/blogs/user/tittles/{tittle}")
    public ResponseEntity findByTitle(@PathVariable String tittle){
        return new ResponseEntity(blogRepository.findByTitleContaining(tittle), HttpStatus.OK);
    }
    @GetMapping("/blogs/user/mode/{mode}")
    public ResponseEntity findByPublicMode(@PathVariable String mode){
        if(mode.equals("public")) {
            return new ResponseEntity(blogRepository.findByMode(mode), HttpStatus.OK);
        }
        return new ResponseEntity(null,HttpStatus.OK);
    }
    @PutMapping("/blogs/user/mode/{id}")
    public ResponseEntity editBlogMode(@PathVariable Long id,@RequestBody String mode){
        Blog blog= blogRepository.findById(id).get();
        blog.setMode(mode);
        blogRepository.save(blog);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/blogs/user/admin")
    public ResponseEntity findAllByAdmin(@RequestBody User user){
        if(user.getUser().equals("admin1")&&user.getPassword().equals("admin")){
            return new ResponseEntity(blogRepository.findAll(), HttpStatus.OK);
        }else {
            return null;
        }
    }
    @DeleteMapping("/blogs/user/admin/{id}")
    public ResponseEntity deleteByAdmin(@PathVariable Long id,@RequestBody User user){
        if(user.getUser().equals("admin1")&&user.getPassword().equals("admin")){
            blogRepository.deleteById(id);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/blogs/user/admin/{title}")
    public ResponseEntity findByTitleContainningByAdmin(@PathVariable String title,@RequestBody User user){
        if(user.getUser().equals("admin1")&&user.getPassword().equals("admin")){
            return new ResponseEntity(blogRepository.findByTitleContaining(title), HttpStatus.OK);
        }else {
            return null;
        }
    }
}
