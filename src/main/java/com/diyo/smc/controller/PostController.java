package com.diyo.smc.controller;

import com.diyo.smc.entity.Post;
import com.diyo.smc.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostByPostId(@PathVariable("postId")Long postId){
        return ResponseEntity.ok(postService.findPostByPostId(postId));
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts(){
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @PostMapping("/addPost/{userId}")
    public ResponseEntity<String> addNewPost(@PathVariable("userId")Long userId,@RequestBody Post post){
        postService.addNewPost(userId,post);
        return ResponseEntity.ok("New Post Added.");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable ("postId") Long postId){

        return ResponseEntity.ok(postService.deletePostById(postId));
    }
    @PatchMapping
    public ResponseEntity<String> editPost(@RequestBody Post post){
        System.out.println(post.getId());
        String response = postService.editPost(post);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Post>> findAllPostsByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(this.postService.findAllPostsByUserId(userId));
    }
}
