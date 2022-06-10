package com.softserve.dao;

import com.softserve.entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book save(Book t);

    Book delete(long id);

    void deleteCopy(long id);

    Book getByID(long id);

    List<Book> getOwnBooks(String action, Long userID);

    List<Book> FindBookByName(String name);

    List<Book> FindBookByAuthor(String name);

    List<Book> FindMostPopular(LocalDate start, LocalDate end);

    List<Book> FindLeastPopular(LocalDate start, LocalDate end);

    List<Book> FindAvailable();

    List<Integer> FindTime(Long userID);

    List<String> getAuthors();

    List<Double> getDuration();

    List<Integer> getCount();

}
