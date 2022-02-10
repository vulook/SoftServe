package com.softserve.dao;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.softserve.entity.Book;

@Transactional
@Repository
public interface BookDAO {

    Optional<Book> getID(long id);

    @SuppressWarnings("unchecked")
    List<Book> getAll();

    void save(Book book);

    void update(Book book, String[] params);

    void delete(Book book);

}

