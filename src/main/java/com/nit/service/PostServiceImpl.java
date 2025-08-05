package com.nit.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nit.bindingclasse.CommentBindingClass;
import com.nit.entity.Comment;
import com.nit.entity.Post;
import com.nit.repository.CommentRepository;
import com.nit.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CommentRepository comRepo;
	
	

	@Override
	public Comment addComment(CommentBindingClass comment) {
		Integer postId=(Integer)session.getAttribute("postId");
		if(!ObjectUtils.isEmpty(postId))
		{
			Comment entity=new Comment();
			entity.setName(comment.getName());
			entity.setPost(postRepo.findById(postId).get());
			entity.setEmail(comment.getEmail());
			entity.setContent(comment.getContent());
			entity.setCommentAddedDate(LocalDate.now());
			
			Comment save = comRepo.save(entity);
			
			session.removeAttribute("postId");
			return save;
		}
		
		return null;
	}
	
	@Override
	public Post readPost(Integer id) {
		session.setAttribute("postId",id);
		return postRepo.findById(id).get();
		
	}
   
	@Override
	public boolean editPost(Post post) {
		  
		Optional<Post> byId = postRepo.findById(post.getId());
		if(byId.isPresent())
		{
			Post old=byId.get();
			old.setTitle(post.getTitle());
			old.setShortDiscription(post.getShortDiscription());
			old.setContent(post.getContent());
			postRepo.save(old);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deletePost(Integer id) {
		
		Optional<Post> byId = postRepo.findById(id);
		if(byId.isPresent())
		{
			Post post=byId.get();
			postRepo.delete(post);
			return true;
		}
		return false;
	}

	@Override
	public List<Post> getAllPost() {
		return postRepo.findAll();
	}

	@Override
	public List<Post> getPostBySearch(String title) {
		List<Post> byTitle = postRepo.findByTitleContainingIgnoreCase(title);
		System.out.println("serach   "+byTitle);
		return byTitle;
	}

	
	@Override
	public List<Comment> getPostsComment(Integer pid) {
		Optional<Post> op=postRepo.findById(pid);
		if(op.isPresent())
		{
			Post post=op.get();
			List<Comment> comments = post.getComments();
			return comments;
		}
			
		return null;
	}
	
	@Override
	public Comment deleteComment(Integer id) {
		Optional<Comment> op=comRepo.findById(id);
		if(op.isPresent())
		{
			Comment comment=op.get();
			comRepo.delete(comment);
			return comment;
		}
		return null;
	}


}
