package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Discussion;
import com.wolfbeisz.model.web.ViewDiscussionRequest;
import com.wolfbeisz.service.DiscussionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Philipp on 19.02.2015.
 */
@Named
@RequestScoped
public class DiscussionController {
    private ViewDiscussionRequest viewDiscussionRequest = new ViewDiscussionRequest();
    private Discussion discussion;

    @Inject
    private DiscussionService discussionService;

    public void loadDiscussion() {
        discussion = discussionService.findDiscussion(viewDiscussionRequest);
    }

    public ViewDiscussionRequest getViewDiscussionRequest() {
        return viewDiscussionRequest;
    }

    public void setViewDiscussionRequest(ViewDiscussionRequest viewDiscussionRequest) {
        this.viewDiscussionRequest = viewDiscussionRequest;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }
}
