package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.AddCommentEvent;
import com.wolfbeisz.model.database.Discussion;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.ViewDiscussionRequest;
import com.wolfbeisz.qualifiers.Authenticated;
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
    private AddCommentEvent addCommentEvent = new AddCommentEvent();

    @Inject
    private DiscussionService discussionService;

    @Inject @Authenticated
    private User user;

    public void loadDiscussion() {
        discussion = discussionService.findDiscussion(viewDiscussionRequest);
    }
    public String addComment() {
        long discussionId = viewDiscussionRequest.getDiscussionId();
        //TODO: do business logic
        addCommentEvent.setDiscussionId(discussionId);
        addCommentEvent.setUserId(user.getId());
        discussionService.createComment(addCommentEvent);

        return "viewDiscussion.xhtml?discussionid="+discussionId+"&faces-redirect=true";
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

    public AddCommentEvent getAddCommentEvent() {
        return addCommentEvent;
    }

    public void setAddCommentEvent(AddCommentEvent addCommentEvent) {
        this.addCommentEvent = addCommentEvent;
    }
}
