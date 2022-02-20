package com.softserve.dao;

import com.softserve.entity.UserRole;

public interface RoleDao {

	UserRole findRoleByName(String theRoleName);
	
}
