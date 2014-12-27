package com.wolfbeisz.service;

import com.wolfbeisz.model.database.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Created by Philipp on 11.11.2014.
 */
@Named
@Stateless
public class UserService {
    @Inject
    private EntityManager em_;

    public Collection<User> findAllUsers() {
        TypedQuery<User> query = em_.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    public User findUser(long id) {
        return null;
    }

    public void add(User user){
        em_.persist(user);
    }
}
