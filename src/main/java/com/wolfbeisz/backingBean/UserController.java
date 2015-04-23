package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.UnfollowUserEvent;
import com.wolfbeisz.event.user.FollowUserEvent;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.ViewUserRequest;
import com.wolfbeisz.qualifiers.Authenticated;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Philipp on 17.02.2015.
 */
@RequestScoped
@Named
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private User user;

    @Inject @Authenticated
    private User authenticatedUser;

    private ViewUserRequest viewRequest = new ViewUserRequest();

    @Inject
    private UserService userService;

    private boolean canFollow = true;
    private boolean canUnfollow = true;

    public String follow() {
        FollowUserEvent followUserEvent = new FollowUserEvent();
        followUserEvent.setUserId(authenticatedUser.getId());
        followUserEvent.setIdolId(viewRequest.getId());
        userService.followUser(followUserEvent);
        logger.debug("method 'follow' called; outcome='"+"viewUser.xhtml?userid="+followUserEvent.getIdolId()+"&faces-redirect=true"+"'");
        return "viewUser.xhtml?userid="+followUserEvent.getIdolId()+"&faces-redirect=true";
    }

    public String unfollow() {
        UnfollowUserEvent unfollowEvent = new UnfollowUserEvent();
        unfollowEvent.setUserId(authenticatedUser.getId());
        unfollowEvent.setIdolId(viewRequest.getId());
        userService.unfollowUser(unfollowEvent);
        return "viewUser.xhtml?userid="+unfollowEvent.getIdolId()+"&faces-redirect=true";
    }
    public void loadUser() {
        user = userService.findUser(viewRequest);
        canFollow = canFollow(authenticatedUser, user);
        canUnfollow = !canFollow(authenticatedUser, user);
    }

    private boolean canFollow(User user, User potentialIdol) {
        for (User u : potentialIdol.getFollowers())
        {
            if (u.getId() == user.getId()) {
                return false;
            }
        }
        return true;
    }

    public boolean getCanFollow() {
        return canFollow;
    }

    public boolean getCanUnfollow() {
        return canUnfollow;
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
