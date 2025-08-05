package com.nit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nit.bindingclasse.CommentBindingClass;
import com.nit.bindingclasse.PostBindingClass;
import com.nit.entity.Comment;
import com.nit.entity.Post;
import com.nit.service.PostService;
import com.nit.service.UserService;

@Controller
public class PostController {
	
	@Autowired
	private PostService postSer;
	
	@Autowired
	private UserService userSer;
	
	
	//Create post 
	@GetMapping("/savePost")
	public String savePostForm(Model model)
	{
		model.addAttribute("post",new PostBindingClass());
		return "post_form";
	}
	
	@PostMapping("/savePost")
	public String savePost(@ModelAttribute PostBindingClass post)
	{
		boolean savePost = userSer.savePost(post);
		if(savePost)
		{
			
		}
		else
		{
			
		}
		return "redirect:profile";
	}
	
	
	// read post
	@GetMapping("/postdetails")
	public String postDetails(@RequestParam Integer id ,Model m)
	{
	    Post post = postSer.readPost(id);
	    List<Comment> postsComment = postSer.getPostsComment(id);
	    
	      if(postsComment.size()==0 || postsComment==null)
	      {
	    	  //no Comments
	    	  m.addAttribute("msg","No Comments Available...");
	      }
	      else
	      {
	    	  m.addAttribute("comments",postsComment);
	      }
	  
	    m.addAttribute("comment",new CommentBindingClass());
	    m.addAttribute("post",post);
		return "post_detail";
	}
	
	//edit post
	@GetMapping("/editPost")
	public String editPost(@RequestParam Integer id,Model m)
	{
		Post post = userSer.getPostById(id);
		m.addAttribute("post",post);
		return "edit_post";
	}
	
	@PostMapping("/editPost")
	public String editPostProc(@ModelAttribute Post post)
	{
		System.out.println(post.getId());
		boolean editPost = postSer.editPost(post);
		if(editPost)
		{
			return "redirect:postdetails?id="+post.getId();
		}
		else
		{
			return "";                //error pag
		}
	}
	
	//delete post
	@GetMapping("/deletePost")
	public String deletePostById(@RequestParam Integer id)
	{
		boolean deletePost = postSer.deletePost(id);
		if(deletePost)
		{
			return "redirect:profile";
		}
		else
		{
			//transfeter not deleted message
			return "redirect:postdetails?id="+id;
		}
		
	}
	
	//save comment
	
	@PostMapping("/saveComment")
	public String saveComment(@ModelAttribute CommentBindingClass comment)
	{
		Comment res = postSer.addComment(comment);
		
		return "redirect:postdetails?id="+res.getPost().getId();
	}
	
	//delete comment
	
	@GetMapping("/deleteComment")
	public String deleteComment(@RequestParam Integer id)
	{
		Comment b=postSer.deleteComment(id);
		if(!ObjectUtils.isEmpty(b))
		{
			return "redirect:postdetails?id="+b.getPost().getId();
		}
		
		
		throw new IllegalArgumentException("Comment not present with given data");
		
		
	}
	


	

	

}
