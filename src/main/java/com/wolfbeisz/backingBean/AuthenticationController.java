package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.UnfollowUserEvent;
import com.wolfbeisz.event.user.FollowUserEvent;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.ViewUserRequest;
import com.wolfbeisz.qualifiers.Authenticated;
import com.wolfbeisz.service.DocumentService;
import com.wolfbeisz.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Philipp on 21.04.2015.
 */
@Named
@SessionScoped
public class AuthenticationController implements Serializable {
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);
    @Inject @Authenticated private User current;

    @Inject
    private transient UserService userService;

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }


    //source: http://stackoverflow.com/questions/5619827/how-to-invalidate-session-in-jsf-2-0
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "browseDocuments.xhtml?faces-redirect=true";
    }

    public boolean isAdministrator() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin");
    }

    /**
     *
     * @param idolToAdd the id of the idol which should be added
     * @return
     */
    public String follow(long idolToAdd) {
        logger.debug("follow "+idolToAdd);
        FollowUserEvent followUserEvent = new FollowUserEvent();
        followUserEvent.setUserId(current.getId());
        followUserEvent.setIdolId(idolToAdd);
        userService.followUser(followUserEvent);

        logger.debug("follow "+idolToAdd+" successful");
        refresh();

        logger.debug("navigating...");

        //logger.debug("method 'follow' called; outcome='"+"viewUser.xhtml?userid="+idolToAdd+"&faces-redirect=true"+"'");
        return "viewUser.xhtml?userid="+idolToAdd+"&faces-redirect=true";
    }

    /**
     *
     * @param idolToRemove the id of the idol which should be removed
     * @return
     */
    public String unfollow(long idolToRemove) {
        UnfollowUserEvent unfollowEvent = new UnfollowUserEvent();
        unfollowEvent.setUserId(current.getId());
        unfollowEvent.setIdolId(idolToRemove);
        userService.unfollowUser(unfollowEvent);

        refresh();

        return "viewUser.xhtml?userid="+idolToRemove+"&faces-redirect=true";
    }

    public boolean canFollow(long idolId) {
        if (current.getId() == idolId)
            return false;

        for (User idol : current.getIdols()) {
            if (idol.getId() == idolId)
                return false;
        }
        return true;
    }

    public boolean canUnfollow(long idolId) {
        for (User idol : current.getIdols()) {
            if (idol.getId() == idolId)
                return true;
        }
        return false;
    }

    public void refresh() {
        ViewUserRequest r = new ViewUserRequest();
        r.setId(current.getId());
        current = userService.findUser(r);
    }
}
