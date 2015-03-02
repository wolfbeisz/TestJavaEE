package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Group;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * Created by Philipp on 01.03.2015.
 */
@Transactional(Transactional.TxType.SUPPORTS)
public class GroupDao {
    @Inject
    private EntityManager em;

    /**
     *
     * @param name the name of the group which is fetched
     * @return
     */
    public Group findGroupByName(String name) {
        TypedQuery<Group> groupByNameQuery = em.createNamedQuery("Group.findByName", Group.class);
        groupByNameQuery.setParameter("name", name);
        return groupByNameQuery.getSingleResult();
    }
}
