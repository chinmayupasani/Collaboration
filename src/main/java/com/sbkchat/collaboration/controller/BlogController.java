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

import com.sbkchat.collaboration.dao.BlogDAO;
import com.sbkchat.collaboration.dao.UserDAO;
import com.sbkchat.collaboration.dto.Blog;
import com.sbkchat.collaboration.dto.User;

@RestController
public class BlogController {
	
	@Autowired
	private BlogDAO blogDAO;

	@Autowired
	private UserDAO userDAO;
	
	// method for creating a new blog
	
	@RequestMapping(value="/blog/new",method=RequestMethod.POST)
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog)
	{
		System.out.println("You are going to add Blog!");
		
		int id = blog.getUserId();
		User user = userDAO.getUser(id);
		if (user.getRole().equals("Super_Admin") || user.getRole().equals("Admin"))
		{
			blog.setStatus("APPROVED");
		}
		else 
		{
			blog.setStatus("PENDING");
		}
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		
		blog.setCreateDate(LocalDate.parse(dateFormat.format(now)));
		blog.setNoOfLikes(0);
		blog.setNoOfViews(0);
		blog.setNoOfComments(0);
		
		blogDAO.addBlog(blog);
		
		System.out.println(blog);
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	// method to update blog
	
	@RequestMapping(value="/blog/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable int id, @RequestBody Blog blog)
	{
		Blog currentBlog = blogDAO.getBlog(id);
		
		currentBlog.setStatus("APPROVED");
		blogDAO.updateBlog(currentBlog);
		
		return new ResponseEntity<Blog>(currentBlog,HttpStatus.OK);
	}
	
	// method to delete blog
	@RequestMapping(value="/blog/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") int id)
	{
		System.out.println("This Method for deleting the blog!");
		Blog blog = blogDAO.getBlog(id);
		
		blogDAO.deleteBlog(blog);
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	// method for viewing single blog by blog id
	
	@RequestMapping(value="/blog/{id}",method=RequestMethod.GET)
	public ResponseEntity<Blog> viewBlog(@PathVariable("id") int id)
	{
		System.out.println("You are Going to view a Blog By Blog ID!");
		
		Blog blog = null;
		
		blog = blogDAO.getBlog(id);
		
		if(blog == null)
		{
			blog = new Blog();
			blog.setErrCode("400");
			blog.setErrMessage("Blog Does not Exist!");
		}
		
		else if(blog.getStatus().equals("APPROVED"))
		{
			blog.setNoOfViews(blog.getNoOfViews() + 1);
		}
		else
		{
			blog.setNoOfViews(0);
		}
		
		blogDAO.updateBlog(blog);
		
		
		
		System.out.println(blog);
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	// method to get the blog list by user id
	
	@RequestMapping(value="/user/blogs/list/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> getUsersBlog(@PathVariable("id") int id)
	{
		System.out.println("Getting the Blog List by User!");
		
		List<Blog> blog = blogDAO.getUsersBlog(id);
		
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}
	
	// method to get the all blog list
	
	@RequestMapping(value="/blog/list",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlog()
	{
		System.out.println("Fetching the list of all Blog");
		
		List<Blog> blog = blogDAO.list();
		
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}
	
	// method for get the blog list by status
	
	@RequestMapping(value="blog/list/status",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> getApprovedBlog(String status)
	{
		System.out.println("Fetching the Blog List by Status = Approved!");
		
		List<Blog> blog = blogDAO.getBlogByStatus("APPROVED");
		
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}
	
	// method get blog list by no of views
	
	@RequestMapping(value="/blog/list/views",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> getBlogListByViews()
	{
		System.out.println("Fetchinf the list of blog by no of views");
		List<Blog> blog = blogDAO.mainList();
		
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}
	
	// method to like blog
	
	@RequestMapping(value="/blog/like/{id}",method=RequestMethod.POST)
	public ResponseEntity<Blog> likeBlog(@PathVariable("id") int id)
	{
		System.out.println("Method for Like a blog!");
		
		Blog blog = new Blog();
		
		blog = blogDAO.getBlog(id);
		int like = blog.getNoOfLikes();
		
		blog.setNoOfLikes(like+1);
		
		blogDAO.updateBlog(blog);
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
}
















