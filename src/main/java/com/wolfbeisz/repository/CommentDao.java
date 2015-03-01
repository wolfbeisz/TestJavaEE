package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Comment;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Created by Philipp on 25.02.2015.
 */
public class CommentDao {
    @Inject
    private EntityManager em;

    @Transactional
    public void create(Comment comment) {
        em.persist(comment);
    }
}
