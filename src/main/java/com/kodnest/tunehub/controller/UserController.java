package com.kodnest.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.UserService;
import com.kodnest.tunehub.serviceimpl.UserServiceImplementation;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	UserServiceImplementation serviceImpl;
	
@PostMapping("/register")
public String addUser(@ModelAttribute User user)
{
	//to check a user with a mail is present or not
	boolean userExists=userService.emailExists(user);
	
	if(userExists==false)
	{
		userService.saveUser(user);
		System.out.println("User added successfully");
	}
	else
	{
		System.out.println("Duplicate User");
	}
	System.out.println("now going to login");
	return "login";
	}


@PostMapping("/validate")
public String validate(@RequestParam("email") String email,
		@RequestParam("password") String password,HttpSession session)
	{
	//email and password is passed from form to user service
	if(userService.validUser(email,password)==true)
	{
		//email is picked up from form and we store it in a session named as "email"; 
		session.setAttribute("email",email);
		String role=userService.getRole(email);
	
		if(role.equals("admin"))
		{
			return "adminhome";
		}else {
			User user=UserService.getUser(email);
			return "customerhome";
		}
	}	
	else
		{
		return "login";
		}
	}

@GetMapping("/logout")
public String logout(HttpSession session) 
{
	session.invalidate();
    return "login";
}
}

		
		