package com.sbkchat.collaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbkchat.collaboration.dao.EventDAO;
import com.sbkchat.collaboration.dto.Events;

@Repository("eventDAO")
@Transactional
public class EventDAOImpl implements EventDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addEvent(Events event) {
		try {
			sessionFactory.getCurrentSession().save(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateEvent(Events event) {
		try {
			sessionFactory.getCurrentSession().update(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteEvent(Events event) {
		try {
			sessionFactory.getCurrentSession().delete(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Events getEvent(int id) {
		
		return sessionFactory.getCurrentSession().get(Events.class, id);
	}

	@Override
	public List<Events> list() {
		
		String selectQuery = "FROM Events";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<Events> getEventByStatus(String status) {
		
		String selectQuery = "FROM Events Where status = '"+status+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<Events> getUsersEvents(int id) {
		
		String selectQuery = "FROM Events Where userId= '"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<Events> mainList() {
		
		String selectQuery = "FROM Events Where status = 'APPROVED' order by postDate";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		query.setFirstResult(0);
		query.setMaxResults(3);
		return query.list();
	}

}
