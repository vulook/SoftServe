package com.softserve.dao;

import com.softserve.entity.Author;

import java.util.List;

public interface AuthorDao {
	
    List<Author> getAll();

    Author save(Author t);

    Author delete(long id);

    Author getByID(long id);
	
}
