package com.softserve.dao.Implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.dao.RoleDao;
import com.softserve.entity.UserRole;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserRole findRoleByName(String theRoleName) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createSQLQuery("SELECT * FROM userrole r where r.Role=:roleName").addEntity(UserRole.class);
		theQuery.setParameter("roleName", theRoleName);
		
		UserRole theRole;
		
		try {
			theRole = (UserRole) theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}
		return theRole;
	}

}
