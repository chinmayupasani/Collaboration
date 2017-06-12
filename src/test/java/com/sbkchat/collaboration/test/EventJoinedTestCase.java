package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.EventJoinedDAO;
import com.sbkchat.collaboration.dto.EventJoined;

public class EventJoinedTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static EventJoined eventJoined;
	
	@Autowired 
	private static EventJoinedDAO eventJoinedDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		eventJoined = (EventJoined) context.getBean("eventJoined");
		eventJoinedDAO = (EventJoinedDAO) context.getBean("eventJoinedDAO");
	}
	
	/*@Test 
	public void eventJoinedTestCase()
	{
		eventJoined.setEventId(1003);
		eventJoined.setJoinedDate(LocalDate.parse("2017-05-06"));
		eventJoined.setStatus("PENDING");
		eventJoined.setUserId(1002);
		eventJoined.setUserName("Mahesh");
		
		assertEquals("Failed to add event joined!",true,eventJoinedDAO.addEventJoined(eventJoined));
		
		eventJoined = eventJoinedDAO.getEventJoined(1002);
		eventJoined.setStatus("APPROVED");
		
		assertEquals("Failed to updtae the event joined!",true, eventJoinedDAO.updateEventJoined(eventJoined));
		
		assertEquals("Failed to delete event joined!",true, eventJoinedDAO.deleteEventJoined(eventJoined));
		
		assertEquals("Failed to get the event joined!",1000, eventJoinedDAO.getEventJoined(1000).getUserId());
	}*/
	
	@Test
	public void getAllEventJoined()
	{
		int size = eventJoinedDAO.list().size();
		
		assertEquals("Failed to get the list of event joined!",3, size);
	}
	
	@Test
	public void getAllEventJoinedByUser()
	{
		int size = eventJoinedDAO.list(1000).size();
		
		assertEquals("Failed to get the list event joined by user!",1, size);
	}
}















