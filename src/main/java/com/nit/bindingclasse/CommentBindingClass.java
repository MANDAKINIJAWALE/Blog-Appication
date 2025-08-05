package com.nit.bindingclasse;

import java.time.LocalDate;

import com.nit.entity.Post;

import lombok.Data;


@Data
public class CommentBindingClass {
	
	private String name;
	private String email;
	private String content;
    private LocalDate commentAddedDate;
	private Post post;


}
