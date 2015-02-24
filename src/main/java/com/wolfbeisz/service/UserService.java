package com.wolfbeisz.service;

import com.wolfbeisz.event.user.FollowUserEvent;
import com.wolfbeisz.event.user.SearchUsersEvent;
import com.wolfbeisz.event.user.UpdateUserEvent;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.ViewUserRequest;
import com.wolfbeisz.repository.UserDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by Philipp on 11.11.2014.
 */
public class UserService {
    @Inject
    private UserDao userDao;

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public User findUser(ViewUserRequest request) {
        User user = userDao.findUser(request.getId());
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return  user;
    }

    public List<User> findUsers(SearchUsersEvent search) {
        String term = search.getTerm();
        if (term == null) {
            //TODO: should we do that
            term = "";
        }
        return userDao.findUsersByName(search.getTerm());
    }

    @Transactional
    public User updateUser(UpdateUserEvent updateUserEvent) {
        User user = userDao.findUser(updateUserEvent.getUserId());

        user.setName(updateUserEvent.getName());
        user.setEmail(updateUserEvent.getEmail());
        user.setPhone(updateUserEvent.getPhone());
        user.setLocation(updateUserEvent.getLocation());
        user.setDescription(updateUserEvent.getDescription());

        return user;
    }

    @Transactional
    public void followUser(FollowUserEvent  followUserEvent)
    {
        /*if (followUserEvent.getUserId() == followUserEvent.getIdolId()) {
            throw new IllegalStateException("a user cannot follow herself/himself/itself");
        }*/

        User user = userDao.findUser(followUserEvent.getUserId());
        User idol = userDao.findUser(followUserEvent.getIdolId());

        //TODO: additional validations

        user.getIdols().add(idol);
        idol.getFollowers().add(user);
    }
}
