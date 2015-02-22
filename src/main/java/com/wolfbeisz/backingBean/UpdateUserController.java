package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.user.UpdateUserEvent;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.ViewUserRequest;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Philipp on 22.02.2015.
 */
@Named
@RequestScoped //TODO: correct scope?
public class UpdateUserController {
    //TODO: inject the currently authenticated user
    @Inject @Example
    private User user;
    //private ViewUserRequest viewRequest = new ViewUserRequest();
    @Inject
    private UserService userService;
    private UpdateUserEvent updateUserEvent = new UpdateUserEvent();

    @PostConstruct
    public void loadUser() {
        //user = userService.findUser(viewRequest);
        //copy properties from user to updateUserEvent
        updateUserEvent.setUserId(user.getId());
        updateUserEvent.setName(user.getName());
        updateUserEvent.setEmail(user.getEmail());
        updateUserEvent.setPhone(user.getPhone());
        updateUserEvent.setLocation(user.getLocation());
        updateUserEvent.setDescription(user.getDescription());
    }

    public String update() {
        User user = userService.updateUser(updateUserEvent);
        return "viewUser.xhtml?userid="+user.getId()+"&faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*
    public ViewUserRequest getViewRequest() {
        return viewRequest;
    }

    public void setViewRequest(ViewUserRequest viewRequest) {
        this.viewRequest = viewRequest;
    }
    */

    public UpdateUserEvent getUpdateUserEvent() {
        return updateUserEvent;
    }

    public void setUpdateUserEvent(UpdateUserEvent updateUserEvent) {
        this.updateUserEvent = updateUserEvent;
    }
}
