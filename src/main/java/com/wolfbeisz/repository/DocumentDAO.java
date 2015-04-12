package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Document;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger();
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

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Document> findDocumentByTitle(String title) {
        TypedQuery<Document> documentQuery = em.createNamedQuery("Document.findByTitle", Document.class);
        documentQuery.setParameter("title", title);
        return documentQuery.getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(Document document) {
        em.persist(document);
    }
}
