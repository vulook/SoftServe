package com.softserve.service;

import com.softserve.entity.Book;
import com.softserve.entity.User;
import com.softserve.validation.UserValidator;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService  extends UserDetailsService {

    Long getId();

    List<User> getReaders();

    List<User> getAll();

    List<String> getStat();

    List<User> getDebtors();

    List<Book> getStatByReader(String action, long id);

    Integer timeWithLibrary(long id);

    User findByUserEmail(String userEmail);

    @Transactional
    void save(UserValidator userValidator);
}
