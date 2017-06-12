package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.JobDAO;
import com.sbkchat.collaboration.dto.Job;

public class JobTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static Job job;
	
	@Autowired
	private static JobDAO jobDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		job = (Job)context.getBean("job");
		jobDAO = (JobDAO)context.getBean("jobDAO");
	}
	
	/*@Test
	public void jobTestCase()
	{
		job.setCompanyName("TCS");
		job.setSubTitle("Post for Devloper");
		job.setAbout("We wil requrire skilled persons and good comunication");
		job.setJobProfile("Devlop web application");
		job.setQualification("10th,12th,BE/B.Tech/Bsc");
		job.setContactInfo("Mumbai");
		job.setStatus("PENDING");
		job.setPostDate(LocalDate.parse("2017-05-01"));
		job.setUserId(1000);
		job.setUserName("Bhayyasaheb");
		
		assertEquals("Failed to add the job!",true,jobDAO.addJob(job));
		
		job = jobDAO.getJob(1002);
		job.setStatus("APPROVED");
		
		assertEquals("Failed to update the job!",true, jobDAO.updateJob(job));
		
		assertEquals("Failed to delete the job!",true, jobDAO.deleteJob(job));
		
		assertEquals("Failed to get the job!",1001, jobDAO.getJob(1002).getUserId());
		
	}*/
	
	@Test
	public void getAllJob()
	{
		int size = jobDAO.list().size();
		
		assertEquals("Failed to get the list of job!",3, size);
	}
	
	@Test
	public void getUserJob()
	{
		int size = jobDAO.getUsersJob(1000).size();
		
		assertEquals("failed to fetch list of job by user id!",2, size);
	}
	
	@Test
	public void getJobByStatus()
	{
		int size = jobDAO.getJobByStatus("APPROVED").size();
		
		assertEquals("failed to get the list of job  by status!",2, size);
	}
	
	@Test
	public void getJobByOrderOfPostDate()
	{
		List<Job> joblist = jobDAO.mainList();
		for(Job j1 : joblist)
		{
			System.out.println(j1.getCompanyName());
		}
		
		assertEquals("Failed to get the job by Post Date!",2, jobDAO.mainList().size());
	}
}















