package com.sbkchat.collaboration.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sbkchat.collaboration.dao.UserDAO;
import com.sbkchat.collaboration.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUser(int id) {
		
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public User validateUser(User user) {
		
		String username = user.getUsername();
		String password = user.getPassword();
		
		String selectQuery = "From User Where username = '"+ username + "' and password = '" + password + "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		
		try {
			user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserByUserName(String username) {
		
		String selectQuery  = "FROM User where username = :username";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		query.setParameter("username", username);
		try {
			System.out.println("Fetching User by User Name");
			return (User) query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	@Override
	public boolean updateUserProfile(String fileName, int id) {
		
		String selectQuery = "UPDATE User SET profile = :filename WHERE id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		query.setParameter("id", id);
		query.setParameter("fileName", fileName);
		
		try {
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<User> list() {
		
		String selectQuery = "FROM User";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<User> list(String status) {
		
		String selectQuery = "FROM User where status = '"+ status +"' ";
		Query query =sessionFactory.getCurrentSession().createQuery(selectQuery);
		return query.list();
	}

	@Override
	public List<User> fetchOnlineFriends(int id) {
		
		String selectQuery = "SELECT * FROM USER_DETAILS WHERE USER_ID IN "
				+ "(SELECT INITIATOR_ID FROM FRIENDS_LIST WHERE (FRIEND_ID = :id) AND STATUS = 'APPROVED' "
				+ "UNION SELECT FRIEND_ID FROM FRIENDS_LIST WHERE (INITIATOR_ID = :id) AND STATUS = 'APPROVED')"
				+ " AND IS_ONLINE = 1";
		
		return sessionFactory
				.getCurrentSession()
				.createNativeQuery(selectQuery,User.class)
				.setParameter("id", id)
				.getResultList();
	}

}
