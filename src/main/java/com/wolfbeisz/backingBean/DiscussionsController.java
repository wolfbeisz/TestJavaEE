package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Discussion;
import com.wolfbeisz.model.web.ListDiscussionsRequest;
import com.wolfbeisz.qualifiers.Example;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * Created by Philipp on 19.02.2015.
 */
@Named
@RequestScoped
public class DiscussionsController {
    @Inject @Example
    private Collection<Discussion> discussions;

    private ListDiscussionsRequest listDiscussionsRequest = new ListDiscussionsRequest();

    public void loadDiscussions() {}

    public Collection<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(Collection<Discussion> discussions) {
        this.discussions = discussions;
    }

    public ListDiscussionsRequest getListDiscussionsRequest() {
        return listDiscussionsRequest;
    }

    public void setListDiscussionsRequest(ListDiscussionsRequest listDiscussionsRequest) {
        this.listDiscussionsRequest = listDiscussionsRequest;
    }
}
