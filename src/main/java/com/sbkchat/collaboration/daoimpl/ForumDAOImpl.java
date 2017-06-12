package com.sbkchat.collaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbkchat.collaboration.dao.ForumDAO;
import com.sbkchat.collaboration.dto.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Forum getForum(int id) {
		
		return sessionFactory.getCurrentSession().get(Forum.class, id);
	}

	@Override
	public List<Forum> list() {
		
		String selectQuery = "FROM Forum";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<Forum> mainList() {
		
		String selectQuery = "FROM Forum order by createDate";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		query.setFirstResult(0);
		query.setMaxResults(3);
		return query.list();
	}

}
