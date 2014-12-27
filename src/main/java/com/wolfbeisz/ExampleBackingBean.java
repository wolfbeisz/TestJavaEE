package com.wolfbeisz;

import com.wolfbeisz.service.UserService;
import com.wolfbeisz.model.database.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Philipp on 04.11.2014.
 */
@Named
@RequestScoped
public class ExampleBackingBean {
    private User user = new User();

    @Inject
    private UserService userService;

    @PostConstruct
    public void initModel() {
        /*user.setName("test");
        user.setEmail("info@wolfbeisz.com");
        user.setPassword("test");*/
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String addUser(){
        userService.add(user);
        return "hello.xhtml";
    }
}
