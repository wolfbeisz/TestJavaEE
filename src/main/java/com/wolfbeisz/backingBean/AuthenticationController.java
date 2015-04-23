package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.User;
import com.wolfbeisz.qualifiers.Authenticated;

import javax.enterprise.context.SessionScoped;
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
    @Inject @Authenticated private User current;

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

        return true;
        //TODO: use application server's capabilities
    }
}
