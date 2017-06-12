package com.sbkchat.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbkchat.collaboration.dao.UserDAO;
import com.sbkchat.collaboration.dto.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
		
	// method to get list of user by status ="APPROVED"
	
	@RequestMapping(value="users/list",method=RequestMethod.GET)
	public ResponseEntity<List<User>> fetchUser()
	{
		System.out.println("Fetching the all user List By status is Approved!");
		
		List<User> user = userDAO.list("APPROVED");
		
		System.out.println(user);
		
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}
	
	// method to create a new User
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		System.out.println("You are Going to register!");
		
		user.setStatus("PENDING");
		user.setEnabled(true);
		user.setOnline(true);
		user.setProfile("no.jpg");
		user.setRole("User");
		
		userDAO.addUser(user);
		
		System.out.println(user);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	// method to update a existing user
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") int id,@RequestBody User user)
	{
		User currentUser = userDAO.getUser(id);
		currentUser.setStatus("APPROVED");
		
		userDAO.updateUser(currentUser);
		
		return new ResponseEntity<User>(currentUser,HttpStatus.OK);
	}
	
	// method to delete user
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id)
	{
		User user = userDAO.getUser(id);
		userDAO.deleteUser(user);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	//method to validate user or for login
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<User> validateUser(@RequestBody User user)
	{
		if(user.getUsername() != null && user.getPassword() != null)
		{
			if(userDAO.validateUser(user) == null)
			{
				user = new User();
				user.setErrCode("204");
				user.setErrMessage("Invalid Credentials");
				
				return new ResponseEntity<User>(user,HttpStatus.NO_CONTENT);
			}
			else
			{
				user = userDAO.getUserByUserName(user.getUsername());
				Boolean status = Boolean.valueOf("true");
				user.setOnline(status);
				user.setErrCode("200");
				user.setErrMessage("You are Login Successfully!");
				userDAO.updateUser(user);
				
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}
		}
		else
		{
			user = new User();
			
			return new ResponseEntity<User>(user,HttpStatus.NO_CONTENT);
		}
	}
	
	// method to get user by user name
	
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	public ResponseEntity<String> checkUser(@RequestBody User username)
	{
		User exitingUser = userDAO.getUserByUserName(username.getUsername());
		if (exitingUser == null)
		{
			return new ResponseEntity<String>("User Does Not Exits!",HttpStatus.NOT_FOUND);
		} 
		else 
		{
			return new ResponseEntity<String>("User Exist",HttpStatus.FOUND);
		}
	}
	
	// method to log out user and set online false
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity<String> toLogout(@RequestBody User user)
	{
		user.setOnline(false);
		userDAO.updateUser(user);
		
		return new ResponseEntity<String>("You are Logout Successfully!",HttpStatus.OK);
	}
}

