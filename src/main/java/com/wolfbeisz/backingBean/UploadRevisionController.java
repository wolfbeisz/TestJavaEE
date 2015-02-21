package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Revision;
import com.wolfbeisz.model.web.AddRevisionRequest;
import com.wolfbeisz.service.RevisionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

/**
 * Created by Philipp on 18.02.2015.
 */
@Named
@RequestScoped
public class UploadRevisionController {
    private AddRevisionRequest addRevisionRequest = new AddRevisionRequest();
    @Inject
    private RevisionService revisionService;

    public String doAddRevision() throws IOException {
        Revision revision = revisionService.addRevision(addRevisionRequest);
        return "viewRevisions.xhtml?documentid="+revision.getDocument().getId()+"&faces-redirect=true";
    }

    public AddRevisionRequest getAddRevisionRequest() {
        return addRevisionRequest;
    }

    public void setAddRevisionRequest(AddRevisionRequest addRevisionRequest) {
        this.addRevisionRequest = addRevisionRequest;
    }
}
