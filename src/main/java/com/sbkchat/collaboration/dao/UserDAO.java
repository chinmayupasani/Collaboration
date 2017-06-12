package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.User;

public interface UserDAO {

	// add the user
	boolean addUser(User user);
	
	// update user
	boolean updateUser(User user);
	
	// delete user
	boolean deleteUser(User user);
	
	//get the user
	User getUser(int id);
	
	// for validating user
	User validateUser(User user);
	
	// get the user by its UserName
	User getUserByUserName(String username);
	
	//update user profile
	boolean updateUserProfile(String fileName, int id);
	
	//list of user
	List<User> list();
	
	//list of user by status
	List<User> list(String status);
	
	// List of (fetch) online User Friends
	List<User> fetchOnlineFriends(int id);
}
