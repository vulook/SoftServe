package com.softserve.dao;

import com.softserve.entity.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book save(Book t);

    Book delete(long id);

    Book deleteCopy(long id);

    Book getByID(long id);

}
