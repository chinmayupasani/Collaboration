package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.Job;

public interface JobDAO {

	// add job
	boolean addJob(Job job);
	
	// update job
	boolean updateJob(Job job);
	
	// delete Job
	boolean deleteJob(Job job);
	
	// get the job by id
	Job getJob(int id);
	
	// list of job
	List<Job> list();
	
	// list of job  by user id
	List<Job> getUsersJob(int id);
	
	// list of job by  status
	List<Job> getJobByStatus(String status);
	
	// list job  by order
	List<Job> mainList();
}
