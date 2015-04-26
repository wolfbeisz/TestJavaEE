package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Activity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Philipp on 26.04.2015.
 */
@Transactional(Transactional.TxType.SUPPORTS)
public class ActivityDao {
    @Inject
    private EntityManager em;

    public List<Activity> findActivitiesOfIdols(long userId) {
        TypedQuery<Activity> query = em.createNamedQuery("Activity.findActivityOfIdols", Activity.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
