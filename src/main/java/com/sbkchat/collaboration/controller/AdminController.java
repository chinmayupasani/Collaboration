package com.sbkchat.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbkchat.collaboration.dao.BlogDAO;
import com.sbkchat.collaboration.dao.EventDAO;
import com.sbkchat.collaboration.dao.JobDAO;
import com.sbkchat.collaboration.dao.UserDAO;
import com.sbkchat.collaboration.dto.Blog;
import com.sbkchat.collaboration.dto.Events;
import com.sbkchat.collaboration.dto.Job;
import com.sbkchat.collaboration.dto.User;

@RestController
public class AdminController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BlogDAO blogDAO;

	@Autowired
	private EventDAO eventDAO;

	@Autowired
	private JobDAO jobDAO;

	// method for fetching the approved user list

	@RequestMapping(value = "/user/manage/list", method = RequestMethod.GET)
	public ResponseEntity<List<User>> fetchApprovedUsers() 
	{
		System.out.println("Starting of the method fetchApprovedUser list");
		
		List<User> userList = userDAO.list("APPROVED");
		
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	// method for changing the user role
	
	@RequestMapping(value="/user/role/manage",method=RequestMethod.POST)
	public ResponseEntity<User> changeUserRole(@RequestBody User user)
	{
		System.out.println("Starting of the method changeUserRole!");
		
		userDAO.updateUser(user);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	// method for fetching approved blog list by status
	
	@RequestMapping(value="/blog/manage/list",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> fetchApprovedBlogs()
	{
		System.out.println("Starting of the method fetchApprovedBlogs!");
		
		List<Blog> blogList = blogDAO.getBlogByStatus("APPROVED");
		
		return new ResponseEntity<List<Blog>>(blogList,HttpStatus.OK);
	}
	
	// method for fetching approved job list by status
	
	@RequestMapping(value="/job/manage/list",method=RequestMethod.GET)
	public ResponseEntity<List<Job>> fetchApprovedJobs()
	{
		System.out.println("Starting of the method fetchApprovedJobs!");
		
		List<Job> jobList = jobDAO.getJobByStatus("APPROVED");
		
		return new ResponseEntity<List<Job>>(jobList,HttpStatus.OK);
	}
	
	// method for fetching Approved Events by status
	
	@RequestMapping(value="/event/manage/list",method=RequestMethod.GET)
	public ResponseEntity<List<Events>> fetchApprovedEvents()
	{
		System.err.println("Starting of the method fetchApprovedEvents!");
		
		List<Events> eventList = eventDAO.getEventByStatus("APPROVED");
		
		return new ResponseEntity<List<Events>>(eventList,HttpStatus.OK);
	}
}

















