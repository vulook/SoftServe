package com.softserve.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import com.softserve.entity.Book;

@Transactional
@Repository
public class IBookDAO implements BookDAO {

    private static final Logger LOG = LoggerFactory.getLogger(IBookDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    public IBookDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Book> getID(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getAll() {
        Query query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void save(Book book) {
        LOG.info("Book with id: " + book.getId()  + " save successfully");
        executeInsideTransaction(entityManager -> entityManager.persist(book));
    }

    @Override
    public void update(Book book, String[] params) {
        book.setId(Long.parseLong((Objects.requireNonNull(params[1], "ID cannot be null"))));
        book.setBookName(Objects.requireNonNull(params[1], "Book Name cannot be null"));
        book.setAuthorId(Long.parseLong((Objects.requireNonNull(params[1], "AuthorId Name cannot be null"))));
        book.setGenre(Objects.requireNonNull(params[3], "Genre cannot be null"));
        book.setCount(Integer.parseInt((Objects.requireNonNull(params[4], "Count cannot be null"))));
        book.setPageCount(Integer.parseInt((Objects.requireNonNull(params[5], "PageCount cannot be null"))));
        book.setRatings(Integer.parseInt((Objects.requireNonNull(params[6], "Ratings cannot be null"))));
        executeInsideTransaction(entityManager -> entityManager.merge(book));
        LOG.info("Book with id: " + book.getId()  + " update successfully");
    }

    @Override
    public void delete(Book book) {
        LOG.info("Book with id: " + book.getId()  + " deleted successfully");
        executeInsideTransaction(entityManager -> entityManager.remove(book));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

//   взято тут
//  https://baeldung-cn.com/java-dao-pattern
//  https://www.baeldung.com/spring-data-entitymanager
//  __________________________________________________________________________________

//  Варіанти
//    @Override
//    public Book getBookByName(String name) {
//        TypedQuery<Book> q = entityManager.createQuery("SELECT b FROM Book b WHERE b.bookName = :title", Book.class);
//        q.setParameter("title", name);
//        return q.getSingleResult();
//    }
//
//    @Override
//    public Book saveBook(Book book) {
//        if (book.getId() == null) {
//            entityManager.persist(book);
//        } else {
//            book = entityManager.merge(book);
//        }
//        LOG.info("Book with id: " + book.getId()  + " save successfully");
//        return book;
//    }
//
//    @Override
//    public void deleteBook(Book book) {
//        if (entityManager.contains(book)) {
//            entityManager.remove(book);
//        } else {
//            entityManager.merge(book);
//        }
//        LOG.info("Book with id: " + book.getId()  + " deleted successfully");
//    }


//    @Override
//    @Transactional
//    public void addBook(Book book) {
//        entityManager.createNativeQuery("INSERT INTO Book (BookName, AuthorId, AuthorId, Genre, Count, PageCount, Ratings) VALUES (?,?,?,?,?,?,?)")
//                .setParameter(1, book.getBookName())
//                .setParameter(2, book.getAuthorById().getFirstName())
//                .setParameter(3, book.getAuthorById().getLastName())
//                .setParameter(4, book.getGenre())
//                .setParameter(5, book.getCount())
//                .setParameter(6, book.getPageCount())
//                .setParameter(7, book.getRatings())
//                .executeUpdate();
//        LOG.info("Book with id: " + book.getId()  + " add successfully");
//    }
//
//    @Override
//    public void deleteBook(long id) {
//        Book book = entityManager.find(Book.class, id);
//        LOG.info("Book with id: " + book.getId()  + " deleted successfully");
//        entityManager.remove(book);
//    }


}

 