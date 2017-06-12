package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.ForumPostDAO;
import com.sbkchat.collaboration.dto.ForumPost;

public class ForumPostTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static ForumPost forumPost;
	
	@Autowired
	private static ForumPostDAO forumPostDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		forumPost = (ForumPost)context.getBean("forumPost");
		forumPostDAO = (ForumPostDAO)context.getBean("forumPostDAO");
	}
	
	/*@Test
	public void forumPostTestCase()
	{
		forumPost.setForumId(1001);
		forumPost.setDescription("This is Forum post");
		forumPost.setPostDate(LocalDate.parse("2017-06-05"));
		forumPost.setTitle("Second Post");
		forumPost.setUserId(1001);
		forumPost.setUserName("Mohan");
		forumPost.setUserProfileId("noImage.jpg");
		
		assertEquals("Failed to add Forum post",true,forumPostDAO.addForumPost(forumPost));
		
		forumPost = forumPostDAO.getForumPost(1003);
		forumPost.setForumId(1003);
		
		assertEquals("Failed to update forum post!",true, forumPostDAO.updateForumPost(forumPost));
		
		assertEquals("Failed to delete Forum post!",true, forumPostDAO.deleteForumPost(forumPost));
		
		assertEquals("Failed to get the Forum post!",1000, forumPostDAO.getForumPost(1000).getUserId());
		
	}*/
	
	@Test
	public void getAllForumPost()
	{
		int size = forumPostDAO.list().size();
		
		assertEquals("Failed to get the list of forum post!",2, size);
	}
	
	@Test
	public void fetchListForumPostByForumId()
	{
		List<ForumPost> listForumPost = forumPostDAO.list(1003);
		for(ForumPost p1: listForumPost)
		{
			System.out.println(p1.getTitle());
		}
		
		assertEquals("Failed to get the list of Forum!",1, forumPostDAO.list(1003).size());
	}
}













