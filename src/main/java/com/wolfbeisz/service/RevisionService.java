package com.wolfbeisz.service;

import com.wolfbeisz.model.database.Revision;
import com.wolfbeisz.model.web.ViewRevisionsRequest;
import com.wolfbeisz.repository.RevisionDao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Philipp on 19.02.2015.
 */
public class RevisionService {

    @Inject
    private RevisionDao revisionDao;

    public List<Revision> findRevisions(ViewRevisionsRequest request) {
        return revisionDao.findRevisions(request.getDocumentid());
    }
}
