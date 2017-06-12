package com.sbkchat.collaboration.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.sbkchat.collaboration.dao.JobAppliedDAO;
import com.sbkchat.collaboration.dao.JobDAO;
import com.sbkchat.collaboration.dao.UserDAO;
import com.sbkchat.collaboration.dto.Job;
import com.sbkchat.collaboration.dto.JobApplied;
import com.sbkchat.collaboration.dto.User;

@RestController
public class JobController {
	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private JobAppliedDAO jobAppliedDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	//method to create a new Job
	
	@RequestMapping(value="/job/new", method=RequestMethod.POST)
	public ResponseEntity<Job> addJob(@RequestBody Job job)
	{
		System.out.println("Starting of the method Addjob!");
		
		int id = job.getUserId();
		
		 User user = userDAO.getUser(id);
		 job.setUserName(user.getUsername());
		 
		 if (user.getRole().equals("Super_Admin") || user.getRole().equals("Admin")) 
		{
			job.setStatus("APPROVED");
		} 
		else 
		{
			job.setStatus("PENDING");
		}
		 
		 DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 LocalDateTime now = LocalDateTime.now();
		 
		 job.setPostDate(LocalDate.parse(dateFormat.format(now)));
		 
		 jobDAO.addJob(job);
		 
		 return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	// method to get the all job list by status
	
	@RequestMapping(value="/job/list/status",method=RequestMethod.GET)
	public ResponseEntity<List<Job>> fetchApprovedJob(String status)
	{
		System.out.println("Starting of the method fetchApprovedJob!");
		
		List<Job> jobList = jobDAO.getJobByStatus("APPROVED");
		
		return new ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
	}
	
	//method for fetching users job
	
	@RequestMapping(value="/user/job/list/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<Job>> fetchUsersJob(@PathVariable("id") int id)
	{
		System.out.println("Starting of the method fetchUsersJob");
		
		List<Job> jobList = jobDAO.getUsersJob(id);
		
		return new ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
	}
	
	// method for applying job
	
	@RequestMapping(value="job/apply/{id}",method=RequestMethod.POST)
	public ResponseEntity<JobApplied> applyJob(@PathVariable("id") int id,@RequestBody JobApplied jobApplied)
	{
		System.out.println("Starting of the method applyJob!");
		
		Job  job = jobDAO.getJob(id);
		
		
		//User user = userDAO.getUser(userId);
		
		//JobApplied jobApplied  = new JobApplied();
		
		jobApplied.setJob(job);
		
		int uId =jobApplied.getUserId();
		User user = userDAO.getUser(uId);
		
		jobApplied.setUserName(user.getUsername());
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now =  LocalDateTime.now();
		
		jobApplied.setAppliedDate(LocalDate.parse(dateFormat.format(now)));
		jobApplied.setStatus("APPROVED");
		
		jobAppliedDAO.addJobApplied(jobApplied);
		
		return new ResponseEntity<JobApplied>(jobApplied, HttpStatus.OK);
	}
	
	// method for fetching the user applied job list
	
	@RequestMapping(value="/user/jobs/applied/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Job>> fetchJobAplied(@PathVariable("id") int id)
	{
		System.out.println("Starting of the method fetchAppliedJob of User!");
		
		List<JobApplied> jobApplied = jobAppliedDAO.list(id);
		List<Job> jobList = new ArrayList<>();
		for (JobApplied j : jobApplied) 
		{
			jobList.add(j.getJob());
		}
		
		return new ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
	}
}















