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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Philipp on 17.02.2015.
 */
@ViewScoped
@Named
public class UserController implements Serializable{

    /** the user to display on the web interface */
    private User user;

    private ViewUserRequest viewRequest = new ViewUserRequest();

    @Inject
    private transient UserService userService;

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
