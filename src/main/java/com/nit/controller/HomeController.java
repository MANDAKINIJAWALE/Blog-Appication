package com.nit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nit.entity.Post;
import com.nit.service.PostService;

@Controller
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	
	//home page list of posts
	@GetMapping
	public String homePage(Model model)
	{
		List<Post> posts= postService.getAllPost();
		model.addAttribute("posts",posts);
		return "home";
	}
	
	//ajax search home page
	@GetMapping("/ajaxSearch")
	public String ajaxSearch(@RequestParam String search,Model m)
	{
		List<Post> postBySearch = postService.getPostBySearch(search);
		m.addAttribute("posts",postBySearch);
		return "ajax_search";
	}
	
	

}
