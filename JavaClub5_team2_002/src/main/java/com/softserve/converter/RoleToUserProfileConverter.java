package com.softserve.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.softserve.entity.UserProfile;
import com.softserve.service.UserProfileService;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile>{

	static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);
	
	@Autowired
	UserProfileService userProfileService;

	public UserProfile convert(Object element) {
		int id = Integer.parseInt((String)element);
		UserProfile profile= userProfileService.findById(id);
		logger.info("Profile : {}",profile);
		return profile;
	}
	
}