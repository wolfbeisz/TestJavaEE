package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.UnfollowUserEvent;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.ViewUserRequest;
import com.wolfbeisz.qualifiers.Authenticated;
import com.wolfbeisz.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Philipp on 22.04.2015.
 */
@Named
@RequestScoped
public class IdolController {
    private  final Logger logger = LogManager.getLogger(IdolController.class);
    @Inject @Authenticated
    private User authenticatedUser;

    @Inject
    private UserService userService;

    private List<User> idols;

    public void loadIdols() {
        ViewUserRequest request = new ViewUserRequest();
        request.setId(authenticatedUser.getId());
        User u = userService.findUser(request);
        idols = u.getIdols();
    }

    public String removeIdol(long idolId) {
        UnfollowUserEvent unfollowUserEvent = new UnfollowUserEvent();
        unfollowUserEvent.setUserId(authenticatedUser.getId());
        unfollowUserEvent.setIdolId(idolId);
        userService.unfollowUser(unfollowUserEvent);
        return "viewIdols.xhtml?faces-redirect=true";
    }


    public List<User> getIdols() {
        return idols;
    }

    public void setIdols(List<User> idols) {
        this.idols = idols;
    }
}
