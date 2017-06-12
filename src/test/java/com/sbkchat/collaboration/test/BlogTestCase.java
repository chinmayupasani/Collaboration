package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.BlogDAO;
import com.sbkchat.collaboration.dto.Blog;

public class BlogTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static BlogDAO blogDAO;
	
	private static Blog blog;
	
	@BeforeClass
	public static void init()
	{
		context  = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
	}
	
	/*@Test
	public void blogTestCase()
	{
		
		blog.setName("Spring MVC");
		blog.setDescription("in this we Spring Framework");
		blog.setStatus("PENDING");
		blog.setCreateDate(LocalDate.parse("2017-06-04"));
		blog.setNoOfComments(20);
		blog.setNoOfLikes(100);
		blog.setNoOfViews(50);	
		blog.setUserId(1002);
		blog.setUsername("Bhayyasaheb");
		
		assertEquals("Failed to add the Blog!",true,blogDAO.addBlog(blog));
		
		blog = blogDAO.getBlog(1004);
		blog.setStatus("APPROVED");
		blog.setCreateDate(LocalDate.parse("2017-06-04"));
		
		assertEquals("Failed to update Blog!",true, blogDAO.updateBlog(blog));
		
		assertEquals("Failed to delete Blog!",true, blogDAO.deleteBlog(blog));
	}*/
	
	@Test
	public void getAllBlogTestCase()
	{
		int size = blogDAO.list().size();
		
		assertEquals("Failed to get the list of blog!",8, size);
	}
	
	@Test
	public void getBlogBystatus()
	{
		int size = blogDAO.getBlogByStatus("PENDING").size();
		assertEquals("Failed to get blog by status!",5, size);
	}
	
	@Test
	public void getBlogByUser()
	{
		int size = blogDAO.getUsersBlog(1002).size();
		assertEquals("failed to get the list of blog by user id!",6, size);
	}
	
	@Test
	public void fetchBlogList()
	{
		List<Blog> bloglist =blogDAO.mainList();
		
		for(Blog b1 : bloglist)
		{
			System.out.println(b1.getNoOfViews());
		}
		assertEquals("Failed to get list of blog by view!",3, blogDAO.mainList().size());
	}
}










