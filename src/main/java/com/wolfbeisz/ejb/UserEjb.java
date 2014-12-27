package com.wolfbeisz.ejb;

import com.wolfbeisz.model.database.User;
import com.wolfbeisz.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * Created by Philipp on 27.12.2014.
 */
@Named
@Stateless
public class UserEjb {
    @Inject
    private UserService service;

    public Collection<User> findAllUsers() {
        return service.findAllUsers();
    }
}
