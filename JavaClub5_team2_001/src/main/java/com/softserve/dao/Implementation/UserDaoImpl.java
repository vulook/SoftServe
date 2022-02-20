package com.softserve.dao.Implementation;

import com.softserve.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserEmail(String userEmail) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createSQLQuery("SELECT * FROM User u where u.Email=:uEmail").addEntity(User.class);
		theQuery.setParameter("uEmail", userEmail);

		User theUser;
		try {
			theUser = (User) theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}
		return theUser;
	}

	@Override
	public void save(User theUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theUser);
	}

}
