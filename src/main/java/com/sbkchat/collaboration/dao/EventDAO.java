package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.Events;

public interface EventDAO {

	// add event
	boolean addEvent(Events event);
	
	// update event
	boolean updateEvent(Events event);
	
	// delete event
	boolean deleteEvent(Events event);
	
	// get event
	Events getEvent(int id);
	
	// get list of event
	List<Events> list();
	
	// get event by status
	List<Events> getEventByStatus(String status);
	
	// get event by user id
	List<Events> getUsersEvents(int id);
	
	// get event by order
	List<Events> mainList();
}
