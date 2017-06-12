package com.sbkchat.collaboration.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbkchat.collaboration.dao.ForumDAO;
import com.sbkchat.collaboration.dao.ForumPostDAO;
import com.sbkchat.collaboration.dao.ForumRequestDAO;
import com.sbkchat.collaboration.dao.UserDAO;
import com.sbkchat.collaboration.dto.Forum;
import com.sbkchat.collaboration.dto.ForumModel;
import com.sbkchat.collaboration.dto.ForumPost;
import com.sbkchat.collaboration.dto.ForumRequest;
import com.sbkchat.collaboration.dto.User;

@RestController
public class ForumController {

	@Autowired
	private Forum forum;
	
	@Autowired
	private ForumDAO forumDAO;
	
	@Autowired
	private ForumPostDAO forumPostDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ForumRequestDAO forumRequestDAO;
	
	// method to create a new Forum 
	
	@RequestMapping(value="forum/new",method = RequestMethod.POST)
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum)
	{
		System.out.println("Starting of the method crate Forum!");
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		
		forum.setCreateDate(LocalDate.parse(dateFormat.format(now)));
		forum.setStatus("APPROVED");
		forum.setNoOfPosts(0);
		
		User user = null;
		int id = forum.getUserId();
		user = userDAO.getUser(id);
		
		forumDAO.addForum(forum);
		
		System.out.println("Adding Forum Successfully!");
		
		// int forumId = forum.getId();
		
		ForumRequest req = new ForumRequest();
		
		req.setUserId(id);
		req.setUserName(user.getUsername());
		req.setStatus("APPROVED");
		req.setForum(forum);
		
		forumRequestDAO.addForumRequest(req);
		
		System.out.println("ForumRequest Added Successfully!");
		
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	
	// method to get the list of Forum
	
	@RequestMapping(value="/forum/list",method=RequestMethod.GET)
	public ResponseEntity<List<Forum>> getAllForum()
	{
		System.out.println("Starting of the method getAllForum!");
		
		List<Forum> forumList = forumDAO.list();
		
		return new ResponseEntity<List<Forum>>(forumList,HttpStatus.OK);
	}
	
	// method to get single Forum by forum id as a parameter
	
	@RequestMapping(value="forum/{id}", method=RequestMethod.GET)
	public ResponseEntity<ForumModel> viewForum(@PathVariable("id") int id)
	{
		System.out.println("Starting of the method view Forum!");
		
		ForumModel forumModel = new ForumModel();
		
		Forum forum = forumDAO.getForum(id);
		
		User user = userDAO.getUser(forum.getUserId());
		
		forumModel.setForum(forum);
		forumModel.setUser(user);
		
		return new ResponseEntity<ForumModel>(forumModel,HttpStatus.OK);
	}
	
	// method to add the forum post to the forum by forum id
	
	@RequestMapping(value="/forum/post/new/{id}", method=RequestMethod.POST)
	public ResponseEntity<ForumPost> addForumPost(@PathVariable("id") int id, @RequestBody ForumPost forumPost)
	{
		System.out.println("Starting of the method addForumPost!");
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		
		forumPost.setPostDate(LocalDate.parse(dateFormat.format(now)));
		
		forum = forumDAO.getForum(id);
		forum.setNoOfPosts(forum.getNoOfPosts() + 1);
		
		forumDAO.updateForum(forum);
		
		forumPost.setForum(forum);
		forumPost.setUserName(userDAO.getUser(forumPost.getUserId()).getUsername());
		forumPost.setUserProfileId(userDAO.getUser(forumPost.getUserId()).getProfile());
		
		forumPostDAO.addForumPost(forumPost);
		
		return new ResponseEntity<ForumPost>(forumPost,HttpStatus.OK);
	}
	
	// method to get the all ForumPost list by ForumId
	
	@RequestMapping(value="/forum/post/list/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ForumPost>> getAllForumPost(@PathVariable("id") int id)
	{
		System.out.println("Starting of the method Get all forum post BY Forum ID!");
		
		List<ForumPost> forumPosts = forumPostDAO.list(id);
		
		return new ResponseEntity<List<ForumPost>>(forumPosts,HttpStatus.OK);
	}
}












