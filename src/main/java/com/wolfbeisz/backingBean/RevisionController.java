package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Revision;
import com.wolfbeisz.model.web.ViewRevisionsRequest;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.service.RevisionService;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Philipp on 17.02.2015.
 */
@Named
@RequestScoped
public class RevisionController {

    private Collection<Revision> revisions;
    private ViewRevisionsRequest viewRequest = new ViewRevisionsRequest();
    @Inject
    private RevisionService revisionService;
    @Inject
    private DocumentController documentController;

    public void next(){}
    public void back(){}

    public void loadRevisions() {
        viewRequest.setDocumentid(documentController.getDocument().getId());
        revisions = revisionService.findRevisions(viewRequest);
    }

    public Collection<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(Collection<Revision> revisions) {
        this.revisions = revisions;
    }

    public ViewRevisionsRequest getViewRequest() {
        return viewRequest;
    }

    public void setViewRequest(ViewRevisionsRequest viewRequest) {
        this.viewRequest = viewRequest;
    }
}
