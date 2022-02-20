package com.softserve.dao;

import com.softserve.entity.User;

public interface UserDao {

    User findByUserEmail(String userEmail);
    
    void save(User user);
    
}
