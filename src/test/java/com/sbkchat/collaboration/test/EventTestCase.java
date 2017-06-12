package com.sbkchat.collaboration.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbkchat.collaboration.dao.EventDAO;
import com.sbkchat.collaboration.dto.Events;

public class EventTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static Events event;
	
	@Autowired
	private static EventDAO eventDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sbkchat.collaboration");
		context.refresh();
		
		event  = (Events) context.getBean("events");
		eventDAO = (EventDAO) context.getBean("eventDAO");
	}
	
	/*@Test
	public void EventTestCase()
	{
		event.setName("Mumbai Rainy Festiwal");
		event.setDescription("This event in Mumbai for the year of 2017");
		event.setVenue("Mumbai");
		event.setStartDate(LocalDate.parse("2017-09-05"));
		event.setEndDate(LocalDate.parse("2017-09-10"));
		event.setPostDate(LocalDate.parse("2017-06-01"));
		event.setStatus("PENDING");
		event.setUserId(1002);
		event.setUserName("Mahesh");
		
		assertEquals("Failed to add event!",true,eventDAO.addEvent(event));
		
		event = eventDAO.getEvent(1003);
		event.setStatus("APPROVED");
		
		assertEquals("Failed to update event!",true,eventDAO.updateEvent(event));
		
		assertEquals("Failed delete event!",true, eventDAO.deleteEvent(event));
		
		assertEquals("Failed to get the event!",1000, eventDAO.getEvent(1000).getUserId());
	}*/
	
	@Test
	public void getAllBlogTestCase()
	{
		int size = eventDAO.list().size();
		
		assertEquals("Failed to get the list of event!",4, size);
	}
	
	@Test
	public void getEventByStatus()
	{
		int size = eventDAO.getEventByStatus("APPROVED").size();
		
		assertEquals("Failed to get the list of event by status!",3, size);
	}
	
	@Test
	public void getUsersEvent()
	{
		int size = eventDAO.getUsersEvents(1002).size();
		
		assertEquals("Failed to get the list of event by user!",2, size);
	}
	
	@Test
	public void fetchEventList()
	{
		List<Events> eventList = eventDAO.mainList();
		for(Events e1 : eventList)
		{
			System.out.println(e1.getName());
		}
		
		assertEquals("Failed to get the list of event where status Approved and order by post date!",3, eventDAO.mainList().size());
	}
}
