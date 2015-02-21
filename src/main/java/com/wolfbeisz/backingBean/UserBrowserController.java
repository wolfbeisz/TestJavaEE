package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.user.SearchUsersEvent;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Philipp on 21.02.2015.
 */
@Named
@ViewScoped
public class UserBrowserController implements Serializable {
    private List<User> users;
    private SearchUsersEvent searchUsersEvent = new SearchUsersEvent();

    @Inject
    private transient UserService userService;

    @PostConstruct
    public void retrieveUsers() {
        users = userService.findAllUsers();
    }

    public void search(){
        users = userService.findUsers(searchUsersEvent);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public SearchUsersEvent getSearchUsersEvent() {
        return searchUsersEvent;
    }

    public void setSearchUsersEvent(SearchUsersEvent searchUsersEvent) {
        this.searchUsersEvent = searchUsersEvent;
    }
}
