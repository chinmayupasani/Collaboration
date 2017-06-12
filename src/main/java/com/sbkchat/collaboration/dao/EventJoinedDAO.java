package com.sbkchat.collaboration.dao;

import java.util.List;

import com.sbkchat.collaboration.dto.EventJoined;

public interface EventJoinedDAO {

	//add event joined
	boolean addEventJoined(EventJoined eventJoined);
	
	// update event joined
	boolean updateEventJoined(EventJoined eventJoined);
	
	// delete event joined
	boolean deleteEventJoined(EventJoined eventJoined);
	
	// get event joined
	EventJoined getEventJoined(int id);
	
	// get list of event joined
	List<EventJoined> list();
	
	// get list of event joined by user id
	List<EventJoined> list(int id);
}
