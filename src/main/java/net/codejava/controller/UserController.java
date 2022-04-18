package net.codejava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
		//working
	    @PostMapping("/process_register")
		public ResponseEntity<User> processRegistration(User user)
		{
			repo.save(user);
			return ResponseEntity.ok(user);
		}
		

		// "Required request parameter 'email' for method parameter type String is not present",
	    @PostMapping("/loginform")
	    public ResponseEntity<Boolean> verify(@RequestParam("email") String email,@RequestParam("password") String password)
	    {
	       User u = null;
	       try 
	    {
			u=repo.findByEmailAndPassword(email,password);
			Long id = u.getUserid();
			System.out.println(id);
		}
		   catch(Exception e)
		{
			System.out.println("user not found");
		}

		   if(u!=null)
		{
			 boolean  value = true;
			 return new ResponseEntity<Boolean>(value, HttpStatus.OK);	
		}
	       else
	    {
	    	   
	    	   boolean  value = false;
	    	   return new ResponseEntity<Boolean>(value, HttpStatus.NOT_ACCEPTABLE);	
	    }	
			
    }
}
