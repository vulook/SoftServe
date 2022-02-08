package com.softserve.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.entity.Book;
import com.softserve.repository.BookRepository;
import com.softserve.exceptions.ResourceNotFoundException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book theBook) {
        bookRepository.save(theBook);
    }

    @Override
    public Book getBook(long id) throws ResourceNotFoundException {
        return bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    public void deleteBook(long theId) {
        bookRepository.deleteById(theId);
    }

}
