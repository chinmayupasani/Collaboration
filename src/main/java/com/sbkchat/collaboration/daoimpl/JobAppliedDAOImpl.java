package com.sbkchat.collaboration.daoimpl;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbkchat.collaboration.dao.JobAppliedDAO;
import com.sbkchat.collaboration.dto.JobApplied;

@Repository("jobAppliedDAO")
@Transactional
public class JobAppliedDAOImpl implements JobAppliedDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addJobApplied(JobApplied jobApplied) {
		try {
			sessionFactory.getCurrentSession().save(jobApplied);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateJobApplied(JobApplied jobApplied) {
		try {
			sessionFactory.getCurrentSession().update(jobApplied);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteJobApplied(JobApplied jobApplied) {
		try {
			sessionFactory.getCurrentSession().delete(jobApplied);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public JobApplied getAppliedJob(int id) {
		
		return sessionFactory.getCurrentSession().get(JobApplied.class, id);
	}

	@Override
	public List<JobApplied> list(int id) {
		
		String selectQuery = "FROM JobApplied Where userId = '"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<JobApplied> list() {
		
		String selectQuery = "FROM JobApplied";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

}
