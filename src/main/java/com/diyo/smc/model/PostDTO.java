package com.diyo.smc.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String post;
    private Date date;
}
