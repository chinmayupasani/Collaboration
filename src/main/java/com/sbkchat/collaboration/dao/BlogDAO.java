package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.Blog;

public interface BlogDAO {

	// add  blog
	boolean addBlog(Blog blog);
	
	// update Blog
	boolean updateBlog(Blog blog);
	
	// delete blog
    boolean deleteBlog(Blog blog);
    
    //get blog
    Blog getBlog(int id);
    
    // get list of blog
    List<Blog> list();
    
    // get list of Blog by status
    List<Blog> getBlogByStatus(String status);
    
    // get list of  blog by user id
    List<Blog> getUsersBlog(int id);
    
    // get blog list where status ="APPROVED" by order of no of views
    List<Blog> mainList();
}
