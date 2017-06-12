package com.sbkchat.collaboration.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbkchat.collaboration.dao.EventDAO;
import com.sbkchat.collaboration.dao.EventJoinedDAO;
import com.sbkchat.collaboration.dao.UserDAO;
import com.sbkchat.collaboration.dto.EventJoined;
import com.sbkchat.collaboration.dto.Events;
import com.sbkchat.collaboration.dto.User;

@RestController
public class EventController {

	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private EventJoinedDAO eventJoinedDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	// method for create event
	
	@RequestMapping(value="/events/new", method=RequestMethod.POST)
	public ResponseEntity<Events> addEvents(@RequestBody Events events)
	{
		System.out.println("Starting of the method AddEvents!");
		
		int id = events.getUserId();
		User user = userDAO.getUser(id);
		events.setUserName(user.getUsername());
		
		if (user.getRole().equals("Super_Admin") || user.getRole().equals("Admin"))
		{
			events.setStatus("APPROVED");
		} 
		else 
		{
			events.setStatus("PENDING");
		}
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		
		events.setPostDate(LocalDate.parse(dateFormat.format(now)));
		
		eventDAO.addEvent(events);
		
		return new ResponseEntity<Events>(events,HttpStatus.OK);
	}
	
	// method for fetching the list of event
	
	@RequestMapping(value="/events/list/status", method=RequestMethod.GET)
	public ResponseEntity<List<Events>> fetchApprovedEvents(String status)
	{
		System.out.println("Starting of the method fetchApprovedEvenrts!");
		
		List<Events> eventsList = eventDAO.getEventByStatus("APPROVED");
		
		return new ResponseEntity<List<Events>>(eventsList,HttpStatus.OK);
	}
	
	// method for fetching the list of events by user
	
	@RequestMapping(value="/user/events/list/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<Events>> fetchUserEvents(@PathVariable("id") int id)
	{
		System.out.println("Starting of the method fetchUserEvents!");
		
		List<Events> eventsList = eventDAO.getUsersEvents(id);
		
		return new ResponseEntity<List<Events>>(eventsList,HttpStatus.OK);
	}
	
	// method for join the event
	
	@RequestMapping(value="/event/join/{id}",method=RequestMethod.POST)
	public ResponseEntity<EventJoined> joinEvent(@PathVariable("id") int id,@RequestBody EventJoined eventJoined)
	{
		System.out.println("Starting of the method joinEvent!");
		
		Events events = eventDAO.getEvent(id);
		//User user = userDAO.getUser(userId);
		
		int uId = eventJoined.getUserId();
		User user = userDAO.getUser(uId);
		
		eventJoined.setUserName(user.getUsername());
		
		//EventJoined eventJoined = new EventJoined();
		
		DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("yyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		
		eventJoined.setJoinedDate(LocalDate.parse(dateFormat.format(now)));
		eventJoined.setEvents(events);
		//eventJoined.setUserId(userId);
		//eventJoined.setUserName(user.getUsername());
		eventJoined.setStatus("APPROVED");
		
		eventJoinedDAO.addEventJoined(eventJoined);
		
		return new ResponseEntity<EventJoined>(eventJoined,HttpStatus.OK);
	}
}
