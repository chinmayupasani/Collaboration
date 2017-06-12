package com.sbkchat.collaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbkchat.collaboration.dao.ForumPostDAO;
import com.sbkchat.collaboration.dto.ForumPost;

@Repository("forumPostDAO")
@Transactional
public class ForumPostDAOImpl implements ForumPostDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addForumPost(ForumPost forumPost) {
		try {
			sessionFactory.getCurrentSession().save(forumPost);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateForumPost(ForumPost forumPost) {
		try {
			sessionFactory.getCurrentSession().update(forumPost);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteForumPost(ForumPost forumPost) {
		try {
			sessionFactory.getCurrentSession().delete(forumPost);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ForumPost getForumPost(int id) {
		
		return sessionFactory.getCurrentSession().get(ForumPost.class, id);
	}

	@Override
	public List<ForumPost> list() {
		
		String selectQuery = "FROM ForumPost";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<ForumPost> list(int id) {
		
		String selectQuery = "FROM ForumPost Where forum_id = '"+ id +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

}
