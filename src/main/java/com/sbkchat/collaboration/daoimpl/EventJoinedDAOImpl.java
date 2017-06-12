package com.sbkchat.collaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbkchat.collaboration.dao.EventJoinedDAO;
import com.sbkchat.collaboration.dto.EventJoined;

@Repository("eventJoinedDAO")
@Transactional
public class EventJoinedDAOImpl implements EventJoinedDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addEventJoined(EventJoined eventJoined) {
		try {
			sessionFactory.getCurrentSession().save(eventJoined);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateEventJoined(EventJoined eventJoined) {
		try {
			sessionFactory.getCurrentSession().update(eventJoined);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteEventJoined(EventJoined eventJoined) {
		try {
			sessionFactory.getCurrentSession().delete(eventJoined);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public EventJoined getEventJoined(int id) {
		
		return sessionFactory.getCurrentSession().get(EventJoined.class, id);
	}

	@Override
	public List<EventJoined> list() {
		
		String selectQuery = "FROM EventJoined";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<EventJoined> list(int id) {
		
		String selectQuery = "FROM EventJoined Where userId = '"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

}
