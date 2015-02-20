package com.wolfbeisz.service;

import com.wolfbeisz.model.database.Discussion;
import com.wolfbeisz.model.web.ListDiscussionsRequest;
import com.wolfbeisz.model.web.ViewDiscussionRequest;
import com.wolfbeisz.repository.DiscussionDao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Philipp on 19.02.2015.
 */
public class DiscussionService {
    @Inject
    private DiscussionDao discussionDao;

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
}
