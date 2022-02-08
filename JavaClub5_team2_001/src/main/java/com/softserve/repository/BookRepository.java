package com.softserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softserve.entity.Book;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, Long> {

}
