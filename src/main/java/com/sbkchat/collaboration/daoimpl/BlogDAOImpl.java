package com.sbkchat.collaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbkchat.collaboration.dao.BlogDAO;
import com.sbkchat.collaboration.dto.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Blog getBlog(int id) {
		
		return sessionFactory.getCurrentSession().get(Blog.class, id);
	}

	@Override
	public List<Blog> list() {
		
		String selectQuery = "FROM Blog";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<Blog> getBlogByStatus(String status) {
		
		String selectQuery = "FROM Blog where status = '"+status+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<Blog> getUsersBlog(int id) {
		String selectQuery = "FROM Blog Where user_id = '"+ id +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}
	
	@Override
	public List<Blog> mainList() {
		
		String selectQuery = "FROM Blog where status = 'APPROVED' order by no_of_Views";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.list();
	}


}
