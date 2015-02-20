package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by Philipp on 18.02.2015.
 */
public class DocumentDAO {
    @Inject
    private EntityManager em;

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Document> findAllDocuments() {
        return em.createNamedQuery("Document.findAll", Document.class).getResultList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Document findDocumentById(long id) {
        return em.find(Document.class, id);
    }

    public List<Document> findDocumentByTitle(String title) {
        TypedQuery<Document> documentQuery = em.createNamedQuery("Document.findByTitle", Document.class);
        documentQuery.setParameter("title", title);
        return documentQuery.getResultList();
    }
}
