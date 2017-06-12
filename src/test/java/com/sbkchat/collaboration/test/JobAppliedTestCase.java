package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.JobAppliedDAO;
import com.sbkchat.collaboration.dto.JobApplied;

public class JobAppliedTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static JobApplied jobApplied;
	
	@Autowired
	private static JobAppliedDAO jobAppliedDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		jobApplied = (JobApplied) context.getBean("jobApplied");
		jobAppliedDAO = (JobAppliedDAO) context.getBean("jobAppliedDAO");
	}
	
	/*@Test
	public void jobAppliedTestCase()
	{
		   jobApplied.setJobId(1004);
		   jobApplied.setStatus("PENDING");
		   jobApplied.setUserId(1002);
		   jobApplied.setUserName("Mahesh");
		   jobApplied.setAppliedDate(LocalDate.parse("2017-06-06"));
		   
		   assertEquals("Failed to add the job Applied!",true,jobAppliedDAO.addJobApplied(jobApplied));
		   
		   jobApplied = jobAppliedDAO.getAppliedJob(1003);
		   jobApplied.setStatus("APPROVED");
		   
		   assertEquals("Failed to update job applied!",true, jobAppliedDAO.updateJobApplied(jobApplied));
		   
		   assertEquals("Failed to delete job applied!",true, jobAppliedDAO.deleteJobApplied(jobApplied));
		   
		   assertEquals("failed to get the job applied!",1000, jobAppliedDAO.getAppliedJob(1000).getUserId());
	}*/
	
	@Test
	public void getAllJobApplied()
	{
		int size = jobAppliedDAO.list().size();
		
		assertEquals("Failed to get the all job applied list!",3, size);
	}
	
	@Test
	public void getAllJobAppliedByUserId()
	{
		int size = jobAppliedDAO.list(1000).size();
		
		assertEquals("Failed to get job applied by user id",1, size);
	}
}














