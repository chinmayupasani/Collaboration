package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.ForumDAO;
import com.sbkchat.collaboration.dao.ForumRequestDAO;
import com.sbkchat.collaboration.dto.Forum;
import com.sbkchat.collaboration.dto.ForumRequest;

public class ForumRequestTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static ForumRequest forumRequest;
	
	@Autowired
	private static ForumRequestDAO forumRequestDAO;
	
	@Autowired
	private static ForumDAO forumDAO;
	
	@Autowired
	private static Forum forum;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		forumRequest = (ForumRequest)context.getBean("forumRequest");
		forumRequestDAO = (ForumRequestDAO)context.getBean("forumRequestDAO");
		forum = (Forum) context.getBean("forum");
		forumDAO = (ForumDAO) context.getBean("forumDAO");
	}
	
	/*@Test
	public void forumRequestTestCase()
	{
		forum = forumDAO.getForum(1006);
		forumRequest.setForum(forum);
		forumRequest.setUserId(1002);
		forumRequest.setUserName("Mahesh");
		forumRequest.setStatus("PENDING");
		
		assertEquals("Failed add the forumrequest!",true,forumRequestDAO.addForumRequest(forumRequest));
		
		//forumRequest = forumRequestDAO.getForumRequest(1002);
		//forumRequest.setStatus("APPROVED");
		
		//assertEquals("Failed to update forum request!",true, forumRequestDAO.updateForumRequest(forumRequest));
		
		//assertEquals("Failed to delete Forum request!",true, forumRequestDAO.deleteForumRequest(forumRequest));
		
		//assertEquals("Failed to get the Forum request!",1000, forumRequestDAO.getForumRequest(1000).getUserId());
	}*/
	
	@Test
	public void getAllForumRequest()
	{
		int size = forumRequestDAO.list().size();
		
		assertEquals("Failed to get the list of forum request!",6, size);
	}
	
	@Test
	public void getAllForumRequestByForumId()
	{
		int size = forumRequestDAO.list(1000).size();
		
		assertEquals("failed to get the ForumRequest By forum Id!",1, size);
	}
	
	@Test
	public void getAllForumRequestByStatus()
	{
		int size = forumRequestDAO.list("PENDING").size();
		
		assertEquals("Failed to get the list of forumrequest by status",2, size);
	}
}
