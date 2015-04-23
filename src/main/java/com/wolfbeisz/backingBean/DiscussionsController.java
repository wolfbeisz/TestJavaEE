package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Discussion;
import com.wolfbeisz.model.web.ListDiscussionsRequest;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.service.DiscussionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Philipp on 19.02.2015.
 */
@Named
@RequestScoped
public class DiscussionsController {

    private Collection<Discussion> discussions = new ArrayList<Discussion>();
    @Inject
    private DiscussionService discussionService;

    private ListDiscussionsRequest listDiscussionsRequest = new ListDiscussionsRequest();

    public void loadDiscussions() {
        discussions = discussionService.findDiscussions(listDiscussionsRequest);
    }

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
