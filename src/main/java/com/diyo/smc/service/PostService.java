package com.diyo.smc.service;

import com.diyo.smc.entity.Comment;
import com.diyo.smc.entity.Post;
import com.diyo.smc.entity.User;
import com.diyo.smc.repository.PostRepository;
import com.diyo.smc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> findAllPostsByUserId(Long userId){
        return this.postRepository.findAllByUserId(userId);
    }

    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }

    public Post findPostByPostId(Long postId){
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            return post.get();
        }
        else{
            return null;
        }
    }
    public void addNewPost(Long userId,Post post){
        Optional<User> userOptional= userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            post.setUser(user);
            postRepository.save(post);
        }
    }
    public String deletePostById(Long postId){
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()){
            postRepository.deleteById(postId);
            return "Post deleted successfully!";
        }
        return "Post not found!";
    }
    public String editPost(Post post){
        Optional<Post> postOptional = postRepository.findById(post.getId());
        if(postOptional.isPresent()){
            Post old = postOptional.get();
            if(post.getPost() != null){
                old.setPost(post.getPost());
            }
            old.setDate(new Date());
            if(post.getNumberOfLikes() >= 0){
                old.setNumberOfLikes(post.getNumberOfLikes());
            }
            if(post.getComments() != null){
                old.setComments(post.getComments());
            }
            if(post.getUser() != null){
                old.setUser(post.getUser());
            }
            postRepository.save(old);
            return "Post modified successfully.";
        }
        return "Post does not exist.";
    }
}
