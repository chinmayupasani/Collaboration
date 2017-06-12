package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.JobApplied;

public interface JobAppliedDAO {

	// add job applied
	boolean addJobApplied(JobApplied jobApplied);
	
	// update job applied
	boolean updateJobApplied(JobApplied jobApplied);
	
	// delete job applied
	boolean deleteJobApplied(JobApplied jobApplied);
	
	// get the applied job
	JobApplied getAppliedJob(int id);
	
	// list of job applied by user id
	List<JobApplied> list(int id);
	
	//list of job applied
	List<JobApplied> list();
}
