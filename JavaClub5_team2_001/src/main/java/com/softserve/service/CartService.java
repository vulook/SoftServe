package com.softserve.service;

import com.softserve.entity.Cart;

import java.util.List;

public interface CartService {

    Cart create(Cart book);

    Cart findByID(Long id);

    Cart delete(Long id);

    void request(Long id);

    void returnBook(long id);

    List<Cart> getAll();

    List<Cart> findAllByID();

}
