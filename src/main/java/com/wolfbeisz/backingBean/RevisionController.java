package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Revision;
import com.wolfbeisz.model.web.ViewRevisionsRequest;
import com.wolfbeisz.qualifiers.Example;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Philipp on 17.02.2015.
 */
@Named
@ViewScoped
public class RevisionController implements Serializable {
    @Inject @Example
    private Collection<Revision> revisions;
    private ViewRevisionsRequest viewRequest = new ViewRevisionsRequest();

    public void next(){}
    public void back(){}

    public void doViewRequest() {}

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
