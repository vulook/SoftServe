package com.softserve.service;

import java.util.List;

import com.softserve.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.entity.Book;
import com.softserve.exceptions.ResourceNotFoundException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> getBooks() {
        return bookDAO.getAll();
    }

    @Override
    public void saveBook(Book theBook) {
        bookDAO.save(theBook);
    }

    @Override
    public Book getBook(long id) throws ResourceNotFoundException {
        return bookDAO.getID(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    public void deleteBook(Book theBook) {
        bookDAO.delete(theBook);
    }

}
