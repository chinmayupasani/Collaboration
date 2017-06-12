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

import com.sbkchat.collaboration.dao.BlogCommentDAO;
import com.sbkchat.collaboration.dao.BlogDAO;
import com.sbkchat.collaboration.dao.UserDAO;
import com.sbkchat.collaboration.dto.Blog;
import com.sbkchat.collaboration.dto.BlogComment;
import com.sbkchat.collaboration.dto.User;

@RestController
public class BlogCommentController {

	@Autowired
	private BlogCommentDAO blogCommentDAO;
	
	@Autowired
	private Blog blog;
	
	@Autowired 
	private BlogDAO blogDAO;
	
	@Autowired
	private User user;
	
	@Autowired
	private UserDAO userDAO;
	
	
	
	// method to add blog comment to blog
	
	@RequestMapping(value="/blog/comment/new/{id}", method=RequestMethod.POST)
	public ResponseEntity<BlogComment> addBlogComment(@PathVariable("id") int id, @RequestBody BlogComment blogComment)
	{
		System.out.println("Adding a new Comment to blog!");
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		
		blogComment.setCommentDate(LocalDate.parse(dateFormat.format(now)));
		
		blog = blogDAO.getBlog(id);
		
		blog.setNoOfComments(blog.getNoOfComments() + 1);
		
		blogDAO.updateBlog(blog);
		
		blogComment.setBlog(blog);
		blogComment.setUserProfileId(userDAO.getUser(blogComment.getUserId()).getProfile());
		
		blogCommentDAO.addBlogComment(blogComment);
		
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
	
	}
	
	// method to fetch the blog comment list
	
	@RequestMapping(value="/blog/comment/list/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getAllBlogcommentList(@PathVariable("id") int id)
	{
		System.out.println("Getting the list of blog comment of blog!");
		
		List<BlogComment> blogComments =  blogCommentDAO.list(id);
		
		return new ResponseEntity<List<BlogComment>>(blogComments, HttpStatus.OK);
	}
}
