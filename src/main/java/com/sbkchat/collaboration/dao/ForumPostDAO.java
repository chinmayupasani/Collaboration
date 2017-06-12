package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.ForumPost;

public interface ForumPostDAO {

	// add forum post
	boolean addForumPost(ForumPost forumPost);
	
	// update forum post
	boolean updateForumPost(ForumPost forumPost);
	
	// delete Forum post
	boolean deleteForumPost(ForumPost forumPost);
	
	// get Forum post
	ForumPost getForumPost(int id);
	
	// list of forum post
	List<ForumPost> list();
	
	// list of forum post by forum id
	List<ForumPost> list(int id);
}
