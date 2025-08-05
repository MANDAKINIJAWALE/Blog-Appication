package com.nit.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nit.bindingclasse.PostBindingClass;
import com.nit.bindingclasse.UserBindingClass;
import com.nit.entity.Comment;
import com.nit.entity.Post;
import com.nit.entity.User;
import com.nit.repository.PostRepository;
import com.nit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
    @Autowired
    private PostRepository postRepo;
	@Autowired
	private HttpSession session;
	
	
	@Override
	public Post getPostById(Integer id) {
		return postRepo.findById(id).get();
	}
	
	@Override
	public boolean savePost(PostBindingClass post) {
		
		Integer uid=(Integer)session.getAttribute("userId");
		
		Optional<User> byId = userRepo.findById(uid);
		
		if(byId.isPresent())
		{
			User user=byId.get();
			Post entity=new Post();
			BeanUtils.copyProperties(post, entity);
			entity.setCreatedDate(LocalDate.now());
			entity.setUpdatedDate(LocalDate.now());
			entity.setUser(user);
			
			 Post savedPost=postRepo.save(entity);
			 if(!ObjectUtils.isEmpty(savedPost))
			 {
				 
				 return true;
			 }
		}
		
		
		return false;
	}

	
	@Override
	public boolean login(String email, String password) {
		
		User user = userRepo.findByEmailAndPassword(email, password);
		if(!ObjectUtils.isEmpty(user))
		{
			//dashboard
			
			session.setAttribute("userId",user.getId());
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public boolean register(UserBindingClass user) {
		
		String email = user.getEmail();
		
		User byEmail = userRepo.findByEmail(email);
		
		if(ObjectUtils.isEmpty(byEmail))
		{
			User entity=new User();
			BeanUtils.copyProperties(user,entity);
			User save=userRepo.save(entity);
			if(save!=null)
			{
				session.setAttribute("userId",save.getId());
				return true;
			}
		}
		
		
		return false;
	}


	@Override
	public List<Post> userPost() {
		Integer id=(Integer)session.getAttribute("userId");
		
	    Optional<User> byId = userRepo.findById(id);
	    if(byId.isPresent())
	    {
	    	User user=byId.get();
	    	List<Post> posts = postRepo.findByUser(user);
	    	return posts;
	    }
		return null;
	}

	@Override
	public List<Post> searchPostsTitleAndUser(String search, Integer id) {
		  Optional<User> byId = userRepo.findById(id);
		  if(byId.isPresent())
		  {
			  User user = byId.get();
			  return postRepo.findByTitleContainingIgnoreCaseAndUser(search, user);
			  
		  }
		throw new IllegalArgumentException("User not present with this id");
	}

	


}
