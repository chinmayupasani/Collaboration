package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.ForumDAO;
import com.sbkchat.collaboration.dto.Forum;

public class ForumTestCase {
	
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static Forum forum;
	
	@Autowired 
	private static ForumDAO forumDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		forum = (Forum)context.getBean("forum");
		forumDAO = (ForumDAO)context.getBean("forumDAO");
	}
	
	/*@Test
	public void ForumTestcase()
	{
		forum.setName("Adv Java");
		forum.setDescription("It is contains Jsp And Servlets ");
		forum.setCreateDate(LocalDate.parse("2017-06-05"));
		forum.setStatus("PENDING");
		forum.setNoOfPosts(50);
		forum.setUserId(1000);
		forum.setUserName("Bhayyasaheb");
		
		assertEquals("Falied to add Forum!",true,forumDAO.addForum(forum));
		
		forum = forumDAO.getForum(1003);
		forum.setStatus("APPROVED");
		
		assertEquals("Failed to update Form!",true,forumDAO.updateForum(forum));
		
		assertEquals("failed to delete forum!",true, forumDAO.deleteForum(forum));
		
		assertEquals("Failed to get the From!",1001, forumDAO.getForum(1001).getUserId());
	}*/
	
	@Test
	public void getAllForum()
	{
		int size = forumDAO.list().size();
		
		assertEquals("Failed to get the list of forum!",4, size);
	}
	
	@Test
	public void getListForumByCreateDate()
	{
		List<Forum> forumlist = forumDAO.mainList();
		for( Forum f1 : forumlist)
		{
			System.out.println(f1.getCreateDate());
		}
		
		assertEquals("Failed to get the list of Forum!",3 ,forumDAO.mainList().size());
	}
}










