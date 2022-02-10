package com.softserve.service;

import java.util.List;

import com.softserve.entity.Book;
import com.softserve.exceptions.ResourceNotFoundException;

public interface BookService {

    List<Book> getBooks();

    void saveBook(Book theBook);

    Book getBook(long id) throws ResourceNotFoundException;

    void deleteBook(Book theBook) throws ResourceNotFoundException;

}
