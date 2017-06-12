package com.sbkchat.collaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbkchat.collaboration.dao.ForumRequestDAO;
import com.sbkchat.collaboration.dto.ForumRequest;

@Repository("forumRequestDAO")
@Transactional
public class ForumRequestDAOImpl implements ForumRequestDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addForumRequest(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().save(forumRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateForumRequest(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().update(forumRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteForumRequest(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().delete(forumRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ForumRequest getForumRequest(int id) {
		
		return sessionFactory.getCurrentSession().get(ForumRequest.class, id);
	}

	@Override
	public List<ForumRequest> list() {
		
		String selectQuery = "FROM ForumRequest";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		 return query.list();
	}

	@Override
	public List<ForumRequest> list(int id) {
		
		String selectQuery = "FROM ForumRequest Where forum_id = '"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<ForumRequest> list(String status) {
		
		String selectQuery = "FROM ForumRequest Where status = '"+ status +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

}
