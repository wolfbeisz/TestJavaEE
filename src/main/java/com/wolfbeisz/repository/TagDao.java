package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Tag;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Created by Philipp on 21.02.2015.
 */
public class TagDao {
    @Inject
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(Tag tag) {
        em.persist(tag);
    }
}
