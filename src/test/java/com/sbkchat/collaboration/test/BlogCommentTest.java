package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.BlogCommentDAO;
import com.sbkchat.collaboration.dto.BlogComment;

public class BlogCommentTest {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	
	private static BlogCommentDAO blogCommentDAO;
	
	
	private static BlogComment blogComment;
	
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		blogCommentDAO = (BlogCommentDAO)context.getBean("blogCommentDAO");
		blogComment = (BlogComment)context.getBean("blogComment");
		
	}
	
	/*@Test
	public void BlogCommentTestCase()
	{
		blogComment.setBlogComment("This is Economical Capital of India");
		blogComment.setCommentDate(LocalDate.parse("2017-06-05"));
		blogComment.setTitle("Mumbai");
		blogComment.setUserId(1000);
		blogComment.setUsername("Bhayyasaheb");
		blogComment.setUserProfileId("noImage.png");
		blogComment.setBlogId(1001);
		
		assertEquals("Failed to add Blog Comment!",true,blogCommentDAO.addBlogComment(blogComment));
		
		blogComment = blogCommentDAO.getBlogComment(1000);
		blogComment.setBlogId(1001);
		
		assertEquals("Failed to update blog Comment!",true, blogCommentDAO.updateBlogComment(blogComment));
		
		assertEquals("Failed to delete Blog Comment!",true, blogCommentDAO.deleteBlogComment(blogComment));
		
		assertEquals("Failed to get Blog Comment by id!",1001, blogComment.getUserId());
		
	}*/
	
	@Test
	public void getBlogCommentBYBlogId()
	{
		int size = blogCommentDAO.list(1003).size();
		
		assertEquals("Failed to get the list of blog comment by blog id!",2, size);
	}
	
	@Test
	public void getAllBlogComment()
	{
		int size = blogCommentDAO.list().size();
		
		assertEquals("Failed to get All Blog Comment List!",3, size);
	}
}















