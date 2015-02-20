package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Discussion;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Philipp on 19.02.2015.
 */
public class DiscussionDao {
    @Inject
    private EntityManager em;

    @Transactional(Transactional.TxType.SUPPORTS)
    public Discussion findDiscussion(long discussionId) {
        return em.find(Discussion.class, discussionId);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Discussion> findDiscussions(long documentid) {
        TypedQuery<Discussion> discussionQuery = em.createNamedQuery("Discussion.findByDocumentId", Discussion.class);
        discussionQuery.setParameter("documentId", documentid);
        return discussionQuery.getResultList();
    }
}
