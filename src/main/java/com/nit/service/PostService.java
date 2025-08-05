package com.nit.service;

import java.util.List;

import com.nit.bindingclasse.CommentBindingClass;
import com.nit.entity.Comment;
import com.nit.entity.Post;

public interface PostService {

	public List<Post> getAllPost();
	public List<Post> getPostBySearch(String title);
	public Post readPost(Integer id);
	public List<Comment> getPostsComment(Integer pid);
	public Comment addComment(CommentBindingClass comment);
	public boolean editPost(Post post);
	public boolean deletePost(Integer id);
	public Comment deleteComment(Integer id);
	
	
}
