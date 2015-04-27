package com.wolfbeisz.service;

import com.wolfbeisz.event.CheckinEvent;
import com.wolfbeisz.model.database.*;
import com.wolfbeisz.model.web.*;
import com.wolfbeisz.qualifiers.Authenticated;
import com.wolfbeisz.qualifiers.Example;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wolfbeisz.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Philipp on 16.12.2014.
 */

public class DocumentService {
    private static final Logger logger = LogManager.getLogger(DocumentService.class);
    @Inject
    private RevisionDao revisionDao;

    //TODO: shouldn't do that as it couples the service and the web tier. The user should be provided by the appropriate value-object
    @Inject @Authenticated
    private User user;

    @Inject
    private DocumentDAO documentDAO;

    @Inject
    private TagService tagService;

    @Inject
    private TagDao tagDao;

    @Inject
    private UserDao userDao;

    @Inject
    private CheckoutDao checkoutDao;

    @Inject
    private RevisionService revisionService;

    @Transactional
    public Document createDocument(AddDocumentRequest request) throws IOException {
        Document d = new Document();
        d.setTitle(request.getTitle());
        d.setCreatedBy(user);
        d.setCreatedStamp(new Timestamp((new java.util.Date()).getTime()));
        documentDAO.create(d);

        String tags = request.getTags();
        if (tags == null) {
            tags = "";
        }
        createTags(d, tags);

        // use ModelUtil.createRevision
        Revision r = new Revision();
        r.setCreatedStamp(new Timestamp((new java.util.Date()).getTime()));
        r.setCreatedBy(user);
        r.setDocument(d);
        r.setFilecontent(org.apache.commons.io.IOUtils.toByteArray(request.getFile().getInputStream()));
        r.setVersion(new BigDecimal(0));
        r.setMimetype(request.getFile().getContentType());
        revisionDao.create(r);

        return d;
    }

    private void createTags(Document document, String commaseparatedTags) {
        for (String tag : tagService.parseTags(commaseparatedTags)) {
            Tag t = new Tag();
            t.setDocument(document);
            t.setText(tag);
            t.setCreatedBy(user);
            t.setCreatedStamp(new Timestamp((new java.util.Date()).getTime()));
            tagDao.create(t);

            document.addTag(t);
        }
    }


    @Transactional(Transactional.TxType.SUPPORTS)
    public Document findDocument(long id) {
        Document document = documentDAO.findDocumentById(id);
        if (document != null) {
            return document;
        }
        throw new IllegalArgumentException();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Document findDocument(ViewDocumentRequest request) {
        return findDocument(request.getDocumentid());
    }

    public List<Document> findAllDocuments() {
        return documentDAO.findAllDocuments();
    }

    public List<Document> findDocuments(DocumentSearchEvent documentSearchEvent) {
        return documentDAO.findDocumentByApproximateTitle(documentSearchEvent.getTerm());
    }

    //TODO: modifying the tags belongs in another view
    //TODO: do not delete all tags and recreate them
    @Transactional
    public Document updateDocument(UpdateDocumentRequest updateDocumentRequest) {
        Document document = documentDAO.findDocumentById(updateDocumentRequest.getId());
        document.setTitle(updateDocumentRequest.getTitle());

        /*
        List<Tag> existingTags = new ArrayList<Tag>(document.getTags());
        for (Tag tag : existingTags) {
            document.getTags().remove(tag);
            tagDao.delete(tag);
        }
        createTags(document, updateDocumentRequest.getTags());
        */
        return document;
    }

    @Transactional
    public Checkout checkOutDocument(long userId, long revisionId) {
        Revision revision = revisionDao.findById(revisionId);
        Document document = revision.getDocument();

        if (existsCheckout(userId, document.getId()))
        {
            throw new IllegalStateException("user (id="+userId+") has already checked out the document (id="+document.getId()+")");
        }

        User user = userDao.findUser(userId);

        Checkout c = new Checkout();
        c.setCreatedStamp(new Date());
        c.setCreatedBy(user);
        c.setRevision(revisionDao.findById(revisionId));

        checkoutDao.create(c);
        return c;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public boolean existsCheckout(long userId, long documentId) {
        return checkoutDao.findByUserAndDocument(userId, documentId).size() > 0;
    }

    @Transactional
    public void checkIn(CheckinEvent event) throws IOException {
        logger.debug("check in: checkoutId="+event.getCheckoutId());
        Checkout checkout = checkoutDao.findById(event.getCheckoutId());

        AddRevisionRequest addRevision = new AddRevisionRequest();
        addRevision.setDocumentid(checkout.getRevision().getDocument().getId());
        addRevision.setFile(event.getFile());
        revisionService.addRevision(addRevision);

        checkoutDao.delete(checkout);
    }
}
