package com.diyo.smc.repository;

import com.diyo.smc.entity.Comment;
import com.diyo.smc.entity.Post;
import com.diyo.smc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
