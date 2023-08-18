package com.diyo.smc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private Date date;
    private String comment;
    private UserDTO userDTO;
    private PostDTO postDTO;
}
