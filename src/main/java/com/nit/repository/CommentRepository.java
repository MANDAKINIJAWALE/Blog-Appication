package com.nit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer>{

}
