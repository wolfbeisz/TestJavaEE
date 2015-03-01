package com.wolfbeisz.service;

import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.database.Revision;
import com.wolfbeisz.model.database.Tag;
import com.wolfbeisz.model.web.DocumentSearchEvent;
import com.wolfbeisz.model.web.UpdateDocumentRequest;
import com.wolfbeisz.model.web.ViewDocumentRequest;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.model.web.AddDocumentRequest;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.wolfbeisz.model.database.User;
import com.wolfbeisz.repository.DocumentDAO;
import com.wolfbeisz.repository.RevisionDao;
import com.wolfbeisz.repository.TagDao;

/**
 * Created by Philipp on 16.12.2014.
 */

public class DocumentService {
    @Inject
    private RevisionDao revisionDao;

    //TODO: shouldn't do that as it couples the service and the web tier. The user should be provided by the appropriate value-object
    @Inject @Example
    private User exampleUser;

    @Inject
    private DocumentDAO documentDAO;

    @Inject
    private TagService tagService;
    @Inject
    private TagDao tagDao;

    @Transactional
    public Document createDocument(AddDocumentRequest request) throws IOException {
        Document d = new Document();
        d.setTitle(request.getTitle());
        d.setCreatedBy(exampleUser);
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
        r.setCreatedBy(exampleUser);
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
            t.setCreatedBy(exampleUser);
            t.setCreatedStamp(new Timestamp((new java.util.Date()).getTime()));
            tagDao.create(t);
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
        return documentDAO.findDocumentByTitle(documentSearchEvent.getTerm());
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
}
