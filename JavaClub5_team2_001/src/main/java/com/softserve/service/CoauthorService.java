package com.softserve.service;

import com.softserve.entity.Author;
import com.softserve.entity.Book;

public interface CoauthorService {

    Author findByID(Author author);

    Book findByID(Book book);

}
