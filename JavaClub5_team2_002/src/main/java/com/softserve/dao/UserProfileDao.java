package com.softserve.dao;

import java.util.List;

import com.softserve.entity.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);

}
