package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.ViewUserRequest;
import com.wolfbeisz.qualifiers.Example;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Philipp on 17.02.2015.
 */
@RequestScoped
@Named
public class UserController {
    @Inject @Example
    private User user;
    private ViewUserRequest viewRequest;

    public void follow() {}
    public void unfollow() {}
    public void loadUser() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ViewUserRequest getViewRequest() {
        return viewRequest;
    }

    public void setViewRequest(ViewUserRequest viewRequest) {
        this.viewRequest = viewRequest;
    }
}
