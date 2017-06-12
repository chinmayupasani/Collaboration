package com.sbkchat.collaboration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbkchat.collaboration.dao.FriendsDAO;
import com.sbkchat.collaboration.dao.UserDAO;
import com.sbkchat.collaboration.dto.Friends;
import com.sbkchat.collaboration.dto.User;

@RestController
public class FriendController {

	@Autowired
	private FriendsDAO friendsDAO;
	
	@Autowired
	private UserDAO userDAO;  
	
	// method to send friend Friends 
	
	@RequestMapping(value="/user/friendRequest/{id}",method=RequestMethod.POST)
	public ResponseEntity<Friends> sendFriendRequest(@PathVariable("id") int id,@RequestBody Friends friends)
	{
		System.out.println("Starting of the method sendFriendRequest!");
		
		//User user = userDAO.getUser(id);
		
		//Friends friends = new Friends();
		friends.setFriendId(id);
		//friends.setInitiatorId(id);
		friends.setStatus("PENDING");
		
		friendsDAO.addFriend(friends);
		
		return new ResponseEntity<Friends>(friends,HttpStatus.OK);
	}
	
	// method to fetch friend request
	
	@RequestMapping(value="/user/friendRequest/list/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<User>> fetchFriendRequest(@PathVariable("id") int userId)
	{
		System.out.println("Starting of the method fetchFriendRequest!");
		
		List<Friends> friends = friendsDAO.list(userId);
		
		List<User> users = new ArrayList<>();	
		
		for (Friends f1 : friends) 
		{
			if (f1.getStatus().equals("PENDING")) 
			{
				User user = userDAO.getUser(f1.getInitiatorId());
				users.add(user);
			}
		}
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	// method to to fetchApproved  friends Request
	
	@RequestMapping(value="/user/friendRequest/approve/{id}",method=RequestMethod.POST)
	public ResponseEntity<Friends> approveRequest(@PathVariable("id") int id,@RequestBody Friends friends)
	{
		System.out.println("Starting of the method approveRequest!");
		
		int fId =friends.getFriendId();
		
		List<Friends> friendList = friendsDAO.list(fId);
		
		List<User> users = new ArrayList<>();
		
		for (Friends f1 : friendList)
		{
			if (f1.getInitiatorId() == id)
			{
				f1.setStatus("APPROVED");
				friendsDAO.updateFriend(f1);
			}
		}
		
		return new ResponseEntity<Friends>(HttpStatus.OK);
	}
	
	// method to fetch FrinedsModel	that are not my friends
	
	@RequestMapping(value="/user/friends/model/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<User>> users(@PathVariable("id") int userId)
	{
		System.out.println("Starting of the method Fetching Friends ");
		
		List<User> users = friendsDAO.noFriends(userId);
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	// method to fetch the my friends
	
	@RequestMapping(value="/my/friends/{id}", method= RequestMethod.GET)
	public ResponseEntity<List<User>> fetchMyFriends(@PathVariable("id") int userId)
	{
		System.out.println("Starting of the method fetchMyFriends!");
		
		List<User> users = friendsDAO.myFriends(userId);
		
		List<User> myFriends = new ArrayList<>();
		
		for (User user1 : users) 
		{
			if (user1.getId() != userId) 
			{
				myFriends.add(user1);
			}
		}
		
		System.out.println("Successfully Fetcing my friends!");
		
		return new ResponseEntity<List<User>>(myFriends,HttpStatus.OK);
	}
}


















