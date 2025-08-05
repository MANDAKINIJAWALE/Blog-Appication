package com.nit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	public User findByEmail(String email);
	public User findByEmailAndPassword(String email,String password);
}
