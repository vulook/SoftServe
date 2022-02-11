package com.softserve.dao.Implementation;

import com.softserve.dao.CartDao;
import com.softserve.entity.Book;
import com.softserve.entity.Cart;
import com.softserve.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Cart> getAllByUser() {
        User user = new User();
        Query query = sessionFactory.getCurrentSession().createSQLQuery("call getID()").addEntity(User.class);
        user = (User) query.getResultList().stream().findFirst().orElse(null);
        Query query1 = sessionFactory.getCurrentSession().createQuery("select c from Cart c where c.CartUser.id=:id", Cart.class);
        query1.setParameter("id", user.getId());
        return query1.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cart> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Cart").getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void request(long id) {
        User user = new User();
        Query query = sessionFactory.getCurrentSession().createSQLQuery("call getID()").addEntity(User.class);
        user = (User) query.getResultList().stream().findFirst().orElse(null);
        Book book = sessionFactory.getCurrentSession().find(Book.class, id);
        Query query1 = sessionFactory.getCurrentSession().createSQLQuery("call RequestBook(:bookname,:uId)");
        query1.setParameter("uId", user.getId());
        query1.setParameter("bookname", book.getBookName());
        query1.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Cart save(Cart t) {
        User user = new User();
        Query query = sessionFactory.getCurrentSession().createSQLQuery("call getID()").addEntity(User.class);
        user = (User) query.getResultList().stream().findFirst().orElse(null);
        t.setCartUser(user);
        sessionFactory.getCurrentSession().saveOrUpdate(t);
        return t;
    }

    @Override
    public Cart delete(long id) {
        Cart cart = getByID(id);
        sessionFactory.getCurrentSession().remove(cart);
        return cart;
    }

    @Override
    public Cart getByID(long id) {
        return sessionFactory.getCurrentSession().find(Cart.class, id);
    }

}
