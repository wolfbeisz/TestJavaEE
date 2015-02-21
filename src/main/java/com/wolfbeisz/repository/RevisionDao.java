package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Revision;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
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

    @Transactional
    public void create(Revision revision) {
        em.persist(revision);
    }

    public BigDecimal findLatestRevisionNo(long documentId) {
        Query query = em.createQuery("SELECT max(r.version) FROM Revision r WHERE r.document.id = " + documentId);
        Object result = query.getSingleResult();
        return (BigDecimal) result;
        /*TypedQuery<Long> namedQuery = em.createNamedQuery("SELECT max(r.version) FROM Revision r WHERE r.document.id = :documentid", Long.class);
        namedQuery.setParameter("documentid", documentId);
        return namedQuery.getSingleResult();*/

    }
}
