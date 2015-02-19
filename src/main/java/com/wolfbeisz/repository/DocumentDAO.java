package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * Created by Philipp on 18.02.2015.
 */
public class DocumentDAO {
    @Inject
    private EntityManager em;

    public Collection<Document> findAllDocuments() {
        return em.createNamedQuery("Document.findAll", Document.class).getResultList();
    }
}
