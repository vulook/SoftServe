package com.softserve.dao.Implementation;

import com.softserve.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.entity.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserEmail(String userEmail) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("select u from User u where u.email =:uEmail", User.class);
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

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return (List<User>) sessionFactory.getCurrentSession().createSQLQuery("select * from user").addEntity(User.class).getResultList();
	}

}
