package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.ForumRequest;

public interface ForumRequestDAO {

	// add forum request
	boolean addForumRequest(ForumRequest forumRequest);
	
	// update forum request
	boolean updateForumRequest(ForumRequest forumRequest);
	
	// delete Forum request
	boolean deleteForumRequest(ForumRequest forumRequest);
	
	// get forum request by id
	ForumRequest getForumRequest(int id);
	
	// list of forum request
	List<ForumRequest> list();
	
	// list of forum request by forum id
	List<ForumRequest> list(int id);
	
	// list of forum request by status
	List<ForumRequest> list(String status);
}
