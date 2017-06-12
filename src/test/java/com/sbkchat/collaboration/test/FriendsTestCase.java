package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.FriendsDAO;
import com.sbkchat.collaboration.dto.Friends;
import com.sbkchat.collaboration.dto.User;

public class FriendsTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static Friends friends;
	
	@Autowired 
	private static FriendsDAO friendsDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		friends = (Friends) context.getBean("friends");
		friendsDAO = (FriendsDAO) context.getBean("friendsDAO");
	}
	
	/*@Test
	public void FriendsTestCase()
	{
		friends.setInitiatorId(1001);
		friends.setFriendId(1003);
		friends.setStatus("PENDING");
		
		assertEquals("Failedto add the friends! ",true,friendsDAO.addFriend(friends));
		
		friends = friendsDAO.getFriend(1003);
		friends.setStatus("APPROVED");
		
		//assertEquals("Failed to update friends!",true, friendsDAO.updateFriend(friends));
		
		//assertEquals("Failed to delete Friends!",true,friendsDAO.deleteFriend(friends));
		
		assertEquals("Failed to get the friends!",1000, friendsDAO.getFriend(1000).getInitiatorId());
	}*/
	
	@Test
	public void getAllFriends()
	{
		int size = friendsDAO.list().size();
		
		assertEquals("Failed to get the list of friends!",4, size);
	}
	
	@Test
	public void getFrinedListByFriend()
	{
		int size = friendsDAO.list(1001).size();
		
		assertEquals("failed to get the list of friend by friend",1,size);
	}
	
	@Test
	public void getFriendListByStatus()
	{
		int size = friendsDAO.list("APPROVED").size();
		
		assertEquals("Failed to get the list of Firiends by status!",3, size);
	}
	
	@Test
	public void testNoFriends()
	{
		List<User> users = friendsDAO.noFriends(1000);
		
		assertEquals("Failed to get list  no friends!",1, users.size());
	}
	
	@Test
	public void testMyFriends()
	{
		List<User> myFriends = friendsDAO.myFriends(1000);
		
		assertEquals("Failed to get the list my friends!",3, myFriends.size());
	}
}




















