package com.diyo.smc.repository;

import com.diyo.smc.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select\n" +
            "    u.id as user_id, u.first_name as first_name,\n" +
            "    u.last_name as last_name, p.date as date, p.id as id,\n" +
            "    p.number_of_likes as number_of_likes, p.post as post, c.id as comment_id, c.user_id as comment_user_id,\n" +
            "    c.date as comment_date, c.comment as comment, e.id as comment_user_id, e.first_name as comment_user_firstName,\n" +
            "    e.last_name as comment_user_last_name\n" +
            "\n" +
            "from\n" +
            "    ((user u join post p on u.id = p.user_id) left join comment c on p.id = c.post_id) left join user e on e.id = c.user_id\n" +
            "where p.user_id = :userId", nativeQuery = true)
    List<Post> findAllByUserId(Long userId);
}
