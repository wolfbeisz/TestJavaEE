package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Revision;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Philipp on 19.02.2015.
 */
public class RevisionDao {
    @Inject
    private EntityManager em;

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Revision> findRevisions(long documentId)
    {
        TypedQuery<Revision> revisionQuery = em.createNamedQuery("Revision.findByDocumentId", Revision.class);
        revisionQuery.setParameter("documentId", documentId);
        return revisionQuery.getResultList();
    }
}
