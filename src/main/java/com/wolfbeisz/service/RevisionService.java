package com.wolfbeisz.service;

import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.database.Revision;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.model.web.AddRevisionRequest;
import com.wolfbeisz.model.web.ViewRevisionsRequest;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.repository.DocumentDAO;
import com.wolfbeisz.repository.RevisionDao;
import com.wolfbeisz.util.ModelUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Philipp on 19.02.2015.
 */
public class RevisionService {

    @Inject
    private RevisionDao revisionDao;
    @Inject @Example
    private User exampleUser;
    @Inject
    private DocumentDAO documentDao;

    public List<Revision> findRevisions(ViewRevisionsRequest request) {
        return revisionDao.findRevisions(request.getDocumentid());
    }

    @Transactional
    public Revision addRevision(AddRevisionRequest addRevisionRequest) throws IOException {
        Document document = documentDao.findDocumentById(addRevisionRequest.getDocumentid());

        Revision r = new Revision();
        r.setCreatedStamp(new Timestamp((new java.util.Date()).getTime()));
        r.setCreatedBy(exampleUser);
        r.setDocument(document);
        r.setFilecontent(org.apache.commons.io.IOUtils.toByteArray(addRevisionRequest.getFile().getInputStream()));

        //TODO: quite hacky solution
        BigDecimal latestRevisionNo = revisionDao.findLatestRevisionNo(document.getId());
        r.setVersion(latestRevisionNo.add(new BigDecimal(1)));
        r.setMimetype(addRevisionRequest.getFile().getContentType());

        revisionDao.create(r);
        return r;
    }
}
