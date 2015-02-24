package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.user.FollowUserEvent;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.ViewUserRequest;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Philipp on 17.02.2015.
 */
@RequestScoped
@Named
public class UserController {
    private User user;

    @Inject @Example
    private User authenticatedUser;

    private ViewUserRequest viewRequest = new ViewUserRequest();

    @Inject
    private UserService userService;

    public String follow() {
        FollowUserEvent followUserEvent = new FollowUserEvent();
        followUserEvent.setUserId(authenticatedUser.getId());
        followUserEvent.setIdolId(viewRequest.getId());
        userService.followUser(followUserEvent);
        return "viewUser.xhtml?userid="+followUserEvent.getIdolId()+"faces-redirect=true";
    }
    public void unfollow() {}
    public void loadUser() {
        user = userService.findUser(viewRequest);
    }

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
