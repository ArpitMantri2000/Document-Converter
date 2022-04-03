package net.codejava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.codejava.Repository.UserRepository;
import net.codejava.model.User;

@Controller
public class UserController {

	@Autowired
	private UserRepository repo;
	

		@GetMapping("")
		public String viewHomePage()
		{
			return "index";
		}
		@GetMapping("/register")
		public String showSignUpForm(Model model)
		{
			model.addAttribute("user",new User());
			return "signup_form";
		}
		
		@PostMapping("/process_register")
		public String processRegistration(User user)
		{
			repo.save(user);
			return "register_success";
		}

		@GetMapping("/login")
		public String getLoginPage()
		{
			return "login";
		}
		

		@PostMapping("/loginform")
	public String verify(@RequestParam("email") String email,@RequestParam("password") String password)
	{
	   User u = null;
	   try 
	       {
			u=repo.findByEmailAndPassword(email,password);
		}
		catch(Exception e)
			{
			System.out.println("user not found");
		}

		if(u!=null)
		{
		return "doc";	
		}
	    else
	    {
	    return "login";
	    }	
			
    }
}
