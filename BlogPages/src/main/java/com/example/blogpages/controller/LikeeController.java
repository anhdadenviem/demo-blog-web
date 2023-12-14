package com.example.blogpages.controller;

import com.example.blogpages.model.Blog;
import com.example.blogpages.model.Likee;
import com.example.blogpages.model.User;
import com.example.blogpages.repository.BlogRepository;
import com.example.blogpages.repository.LikeeRepository;
import com.example.blogpages.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@CrossOrigin("*")
@RequestMapping("/like")
public class LikeeController {
    private LikeeRepository likeeRepository;
    private BlogRepository blogRepository;
    private UserRepository userRepository;

    @Autowired
    public LikeeController(BlogRepository blogRepository, LikeeRepository likeeRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.likeeRepository = likeeRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/{userId}/{blogId}")
    public ResponseEntity getLikeByBlog(@PathVariable Long userId,@PathVariable Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Likee likees = likeeRepository.findByBlogAndUser(blog,user);
        return new ResponseEntity(likees,HttpStatus.OK);
    }
    @GetMapping("/blog/{blogId}/likes/count")
    public ResponseEntity getLikeCount(@PathVariable Long blogId){
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if(blog == null){
            return ResponseEntity.notFound().build();
        }
        Long likeCount = likeeRepository.countByBlog(blog);
        blog.setLikee(likeCount);
        blogRepository.save(blog);
        return new ResponseEntity(likeCount,HttpStatus.OK);
    }
    @GetMapping("/blog/{blogId}/likes")
    public ResponseEntity getUsersIsLiked(@PathVariable Long blogId){
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if(blog == null){
            return ResponseEntity.notFound().build();
        }
        List<Likee> usersLike = likeeRepository.findByBlog(blog);
        return new ResponseEntity(usersLike,HttpStatus.OK);
    }
    @PostMapping("/{userId}/{blogId}")
    public ResponseEntity like(@PathVariable Long userId,@PathVariable Long blogId){
        Blog blog = blogRepository.findById(blogId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if(blog == null){
            return ResponseEntity.notFound().build();
        }
        Likee likee = likeeRepository.findByBlogAndUser(blog,user);
        if(likee == null){
            Likee likeed = new Likee();
            likeed.setBlog(blog);
            likeed.setUser(user);
            likeeRepository.save(likeed);
        }

        return new ResponseEntity("Đã thích bài viết",HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/{blogId}")
    public ResponseEntity unLike(@PathVariable Long userId,@PathVariable Long blogId){
        Blog blog = blogRepository.findById(blogId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if(blog == null){
            return ResponseEntity.notFound().build();
        }
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        Likee likee = likeeRepository.findByBlogAndUser(blog,user);
        if(likee != null){
            likeeRepository.delete(likee);
        }
        return new ResponseEntity("Đã bỏ thích bài viết",HttpStatus.OK);
    }

}
