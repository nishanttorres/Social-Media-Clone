package com.diyo.smc.service;

import com.diyo.smc.entity.Comment;
import com.diyo.smc.entity.Post;
import com.diyo.smc.entity.User;
import com.diyo.smc.model.CommentDTO;
import com.diyo.smc.repository.CommentRepository;
import com.diyo.smc.repository.PostRepository;
import com.diyo.smc.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    public List<CommentDTO> findAllComments() {
        List<Comment> comments= commentRepository.findAll();
        Type listType = new TypeToken<List<CommentDTO>>() {}.getType();
        return modelMapper.map(comments, listType);
    }
    public Comment findCommentById(Long id){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isPresent()){
            return optionalComment.get();
        }
        return null;
    }

    public void addNewComment(Long userId, Long postId,Comment comment){
        Optional<Post> postOptional = postRepository.findById(postId);
        Optional<User> userOptional = userRepository.findById(userId);
        if(postOptional.isPresent() && userOptional.isPresent()){
            Post post = postOptional.get();
            User user = userOptional.get();
            comment.setPost(post);
            comment.setUser(user);
            commentRepository.save(comment);
        }
    }

    public String deleteCommentById(Long id){
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isPresent()){
            commentRepository.deleteById(id);
            return "Comment deleted successfully";
        }
        return "Comment not found!";
    }
    public String editComment(Comment comment){
        Optional<Comment> commentOptional = commentRepository.findById(comment.getId());
        if(commentOptional.isPresent()){
            Comment old = commentOptional.get();
            if(comment.getComment() != null){
                old.setComment(comment.getComment());
            }
            old.setDate(new Date());
            commentRepository.save(old);
            return "Comment modified successfully";
        }
        return "Comment not found!";
    }
}
