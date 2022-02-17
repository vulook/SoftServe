package com.softserve.entity;

import java.io.Serializable;

public enum UserProfileType implements Serializable{

	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	final String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
