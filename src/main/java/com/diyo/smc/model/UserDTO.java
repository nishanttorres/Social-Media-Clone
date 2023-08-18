package com.diyo.smc.model;

import com.diyo.smc.entity.Comment;
import com.diyo.smc.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
}
