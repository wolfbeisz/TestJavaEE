package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.user.CreateUserEvent;
import com.wolfbeisz.service.UserService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 * Created by Philipp on 01.03.2015.
 */
@Model
public class CreateUserController {
    @Inject
    private UserService userService;

    private CreateUserEvent createUserEvent = new CreateUserEvent();

    public void createUser() {
        userService.createUser(createUserEvent);
    }


    public CreateUserEvent getCreateUserEvent() {
        return createUserEvent;
    }

    public void setCreateUserEvent(CreateUserEvent createUserEvent) {
        this.createUserEvent = createUserEvent;
    }
}
