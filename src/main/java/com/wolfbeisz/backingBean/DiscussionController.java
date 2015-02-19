package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.web.ViewDiscussionRequest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by Philipp on 19.02.2015.
 */
@Named
@RequestScoped
public class DiscussionController {
    private ViewDiscussionRequest viewDiscussionRequest = new ViewDiscussionRequest();
    public void loadDiscussion() {}

    public ViewDiscussionRequest getViewDiscussionRequest() {
        return viewDiscussionRequest;
    }

    public void setViewDiscussionRequest(ViewDiscussionRequest viewDiscussionRequest) {
        this.viewDiscussionRequest = viewDiscussionRequest;
    }
}
