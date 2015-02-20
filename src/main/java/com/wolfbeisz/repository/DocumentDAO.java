package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by Philipp on 18.02.2015.
 */
public class DocumentDAO {
    @Inject
    private EntityManager em;

    @Transactional(Transactional.TxType.SUPPORTS)
    public Collection<Document> findAllDocuments() {
        return em.createNamedQuery("Document.findAll", Document.class).getResultList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Document findDocumentById(long id) {
        return em.find(Document.class, id);
    }
}
