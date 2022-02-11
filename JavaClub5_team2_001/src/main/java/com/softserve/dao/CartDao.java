package com.softserve.dao;

import com.softserve.entity.Cart;

import java.util.List;

public interface CartDao {

    List<Cart> getAll();

    Cart save(Cart t);

    Cart delete(long id);

    Cart getByID(long id);

    void request(long id);

}
