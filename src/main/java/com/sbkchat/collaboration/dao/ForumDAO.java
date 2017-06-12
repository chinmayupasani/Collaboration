package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.Forum;

public interface ForumDAO {

	// add forum
	boolean addForum(Forum forum);
	
	//update forum
	boolean updateForum(Forum forum);
	
	// delete Forum
	boolean deleteForum(Forum forum);
	
	// get Forum by id
	Forum getForum(int id);
	
	// list of all forum
	List<Forum> list();
	
	// list of forum by create date
	List<Forum> mainList();
}
