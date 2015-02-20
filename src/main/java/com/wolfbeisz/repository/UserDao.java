package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
}
