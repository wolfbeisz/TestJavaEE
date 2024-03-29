package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.discussion.CreateDiscussionEvent;
import com.wolfbeisz.model.database.Discussion;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.qualifiers.Authenticated;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.service.DiscussionService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by Philipp on 25.02.2015.
 */
@Named
@RequestScoped
public class CreateDiscussionController {
    private CreateDiscussionEvent createDiscussionEvent = new CreateDiscussionEvent();
    @Inject @Authenticated
    private User user;

    @Inject
    private DocumentController documentController;

    @Inject
    private DiscussionService service;
    public String createDiscussion() {
        createDiscussionEvent.setDocumentId(documentController.getDocument().getId());
        createDiscussionEvent.setTimestamp(new Date());
        createDiscussionEvent.setUserId(user.getId());

        Discussion discussion = service.createDiscussion(createDiscussionEvent);
        return "viewDiscussion.xhtml?discussionid="+discussion.getId()+"&faces-redirect=true";
    }

    public CreateDiscussionEvent getCreateDiscussionEvent() {
        return createDiscussionEvent;
    }

    public void setCreateDiscussionEvent(CreateDiscussionEvent createDiscussionEvent) {
        this.createDiscussionEvent = createDiscussionEvent;
    }
}
