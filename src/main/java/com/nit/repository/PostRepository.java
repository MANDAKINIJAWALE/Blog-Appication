package com.nit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Post;
import com.nit.entity.User;

public interface PostRepository extends JpaRepository<Post,Integer>
{
    public List<Post> findByUser(User user);
    public List<Post> findByTitleContainingIgnoreCase(String title);
    public List<Post> findByTitleContainingIgnoreCaseAndUser(String search,User user);
}
