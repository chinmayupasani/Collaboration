package com.sbkchat.collaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbkchat.collaboration.dao.JobDAO;
import com.sbkchat.collaboration.dto.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Job getJob(int id) {

		return sessionFactory.getCurrentSession().get(Job.class, id);
	}

	@Override
	public List<Job> list() {
		
		String selectQuery = "FROM Job";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	
	@Override
	public List<Job> getUsersJob(int id) {

		String selectQuery = "FROM Job where user_id = '"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<Job> getJobByStatus(String status) {
		
		String selectQuery = "FROM Job where status = '"+status+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<Job> mainList() {
		
		String selectQuery = "FROM Job where status='APPROVED' order by postDate";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.list();
	}

}
