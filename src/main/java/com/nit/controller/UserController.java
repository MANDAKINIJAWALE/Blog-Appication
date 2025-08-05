package com.nit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.bindingclasse.PostBindingClass;
import com.nit.bindingclasse.UserBindingClass;
import com.nit.entity.Post;
import com.nit.service.PostService;
import com.nit.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userSer;
	
	@Autowired
	private PostService postSer;
	
	@Autowired
	private HttpSession session;
	
	
	// user register
	@GetMapping("/register")
	public String registerForm(Model model)
	{
		model.addAttribute("user", new UserBindingClass());
		return "register_user";
	}
	
	@PostMapping("/register")
	public String registerPro(@ModelAttribute UserBindingClass user,RedirectAttributes rd)
	{
		boolean res = userSer.register(user);
		
		if(res)
		{
			return "redirect:/";
		}
		else
		{
			rd.addFlashAttribute("msg","User already exists with this mail..");
			return "redirect:register";
		}
		
		
	}
	
	// user login
	@GetMapping("/login")
	public String loginForm()
	{
		return "login_form";
	}
	
	@PostMapping("/login")
	public String loginProc(@RequestParam String username,@RequestParam String password,RedirectAttributes rd)
	{
		boolean b = userSer.login(username, password);
		if(b)
		{
			return "redirect:/";
		}
		else
		{
			rd.addFlashAttribute("msg","Invalid Credentials! ");
			return "redirect:login";
		}
		
	}
	
	
	// user profile
	@GetMapping("/profile")
	public String dashboard(Model model)
	{
		List<Post> userPost = userSer.userPost();
		model.addAttribute("posts",userPost);
		return "profile";
	}
	
	@GetMapping("/ajaxSearchByUser")
	public String ajaxSearchByUser(@RequestParam String search,Model m)
	{
		System.out.println(session.getAttribute("userId")+" "+search);
		List<Post> list = userSer.searchPostsTitleAndUser(search,(Integer) session.getAttribute("userId") );
		m.addAttribute("posts",list);
		
		return "ajax_search";
	}
	

	//logout session expired
	@GetMapping("/logout")
	public String logout()
	{
		session.invalidate();
		return "redirect:login";
	}

}
