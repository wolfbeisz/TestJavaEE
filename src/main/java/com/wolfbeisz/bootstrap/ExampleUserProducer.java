package com.wolfbeisz.bootstrap;

import com.wolfbeisz.model.database.User;
import com.wolfbeisz.qualifiers.Example;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by Philipp on 16.12.2014.
 */

public class ExampleUserProducer {

    @Inject
    private EntityManager entityManager;

    @Produces @Example
    public User findUser()
    {
        TypedQuery<User> userTypedQuery = entityManager.createNamedQuery("User.findByEmail", User.class);
        userTypedQuery.setParameter("email", "philipp@wolfbeisz.com");
        return userTypedQuery.getSingleResult();
    }
}
