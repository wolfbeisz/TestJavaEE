package com.wolfbeisz.service;

import com.wolfbeisz.event.discussion.CreateDiscussionEvent;
import com.wolfbeisz.model.database.Comment;
import com.wolfbeisz.model.database.Discussion;
import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.ListDiscussionsRequest;
import com.wolfbeisz.model.web.ViewDiscussionRequest;
import com.wolfbeisz.repository.CommentDao;
import com.wolfbeisz.repository.DiscussionDao;
import com.wolfbeisz.repository.DocumentDAO;
import com.wolfbeisz.repository.UserDao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Philipp on 19.02.2015.
 */
public class DiscussionService {
    @Inject
    private DiscussionDao discussionDao;
    @Inject
    private CommentDao commentDao;
    @Inject
    private UserDao userDao;
    @Inject
    private DocumentDAO documentDao;

    public Discussion findDiscussion(ViewDiscussionRequest request) {
        Discussion discussion = discussionDao.findDiscussion(request.getDiscussionId());
        if (discussion == null) {
            throw new IllegalArgumentException();
        }
        return discussion;
    }

    public List<Discussion> findDiscussions(ListDiscussionsRequest request) {
        return discussionDao.findDiscussions(request.getDocumentid());
    }

    @Transactional
    public Discussion createDiscussion(CreateDiscussionEvent createDiscussionEvent) {
        Document document = documentDao.findDocumentById(createDiscussionEvent.getDocumentId());
        User user = userDao.findUser(createDiscussionEvent.getUserId());

        Discussion discussion = new Discussion();
        discussion.setCreatedBy(user);
        discussion.setCreatedStamp(createDiscussionEvent.getTimestamp());
        //TODO: add property 'topic' in model 'Discussion'
        //discussion.setTopic(createDiscussionEvent.getTopic());
        discussionDao.create(discussion);

        Comment comment = new Comment();
        comment.setCreatedBy(user);
        comment.setCreatedStamp(createDiscussionEvent.getTimestamp());
        comment.setPosition(new BigDecimal(0));
        comment.setText(createDiscussionEvent.getFirstPost());
        commentDao.create(comment);

        //create relationships
        document.addDiscussion(discussion);
        discussion.addComment(comment);

        return discussion;
    }
}
