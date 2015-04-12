package com.wolfbeisz.bootstrap;

import com.wolfbeisz.model.database.User;
import com.wolfbeisz.qualifiers.Authenticated;
import com.wolfbeisz.repository.UserDao;
import com.wolfbeisz.service.UserService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by Philipp on 02.03.2015.
 */

public class UserProducer {
    @Inject
    private java.security.Principal principal;

    @Inject
    private UserDao userDao;

    @Produces @Authenticated
    public User authenticatedUser() {
        return userDao.findUserByEmail(principal.getName());
    }
}
