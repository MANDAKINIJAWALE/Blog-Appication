package com.nit.service;

import java.util.List;

import com.nit.bindingclasse.CommentBindingClass;
import com.nit.bindingclasse.PostBindingClass;
import com.nit.bindingclasse.UserBindingClass;
import com.nit.entity.Comment;
import com.nit.entity.Post;

public interface UserService {
	
	public boolean register(UserBindingClass user);
	public boolean login(String username, String password);
	public List<Post> userPost();
	
	public boolean savePost(PostBindingClass post);

	public Post getPostById(Integer id);
	
	public List<Post> searchPostsTitleAndUser(String search,Integer id);
	
	
	
	
	
	
}
