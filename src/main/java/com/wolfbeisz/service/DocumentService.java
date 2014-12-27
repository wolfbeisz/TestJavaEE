package com.wolfbeisz.service;

import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.database.Revision;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.model.web.AddDocumentRequest;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import com.wolfbeisz.model.database.User;

/**
 * Created by Philipp on 16.12.2014.
 */
@Transactional
public class DocumentService {
    @Inject
    private EntityManager entityManager;

    @Inject @Example
    private User exampleUser;


    public void createDocument(AddDocumentRequest request) throws IOException {
        Document d = new Document();
        Revision r = new Revision();

        d.setTitle(request.getTitle());
        d.setCreatedBy(exampleUser);
        d.setCreatedStamp(new Timestamp((new java.util.Date()).getTime()));

        entityManager.persist(d);

        r.setCreatedStamp(new Timestamp((new java.util.Date()).getTime()));
        r.setCreatedBy(exampleUser);
        r.setDocument(d);
        r.setFilecontent(org.apache.commons.io.IOUtils.toByteArray(request.getFile().getInputStream()));
        r.setVersion(new BigDecimal(0));
        r.setMimetype(request.getFile().getContentType());

        entityManager.persist(r);
    }


}
