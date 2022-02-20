package com.softserve.service;

import com.softserve.entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    Book create(Book book);

    Book findByID(Long id);

    Book delete(Long id);

    Book deleteCopy(Long id);

    List<Book> findAll();

    List<Book> findBookByUser(String action);

    List<Book> findBookByName(String name);

    List<Book> findBookByAuthor(String name);

    List<Book> findPopular(LocalDate start, LocalDate end);

    List<Book> findUnpopular(LocalDate start, LocalDate end);

    List<Book> findAvailable();

    List<Integer> findTime();

    List<String> getAuthors();

    List<Double> getDuration();

    List<Integer> getCount();

}
