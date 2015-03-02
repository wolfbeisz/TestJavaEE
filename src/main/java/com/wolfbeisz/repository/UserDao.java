package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Philipp on 19.02.2015.
 */
public class UserDao {
    @Inject
    private EntityManager em;

    @Transactional(Transactional.TxType.SUPPORTS)
    public User findUser(long id) {
        return em.find(User.class, id);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<User> findUsersByName(String name) {
        TypedQuery<User> usersByNameQuery = em.createNamedQuery("User.findByName", User.class);
        usersByNameQuery.setParameter("name", name);
        return usersByNameQuery.getResultList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<User> findAllUsers() {
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(User user) {
        em.persist(user);
    }
}
