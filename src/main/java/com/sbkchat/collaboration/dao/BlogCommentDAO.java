package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.BlogComment;

public interface BlogCommentDAO {
	
	// add blog comment
	boolean addBlogComment(BlogComment blogComment);
	
	// update blog comment
	boolean updateBlogComment(BlogComment blogComment);
	
	// delete blog comment
	boolean deleteBlogComment(BlogComment blogcomment);
	
	// get blog comment by id
	BlogComment getBlogComment(int id);
	
	// get list of blog comment
	List<BlogComment> list();
	
	// get list of Blog comment by blog id
	List<BlogComment> list(int id);
}
