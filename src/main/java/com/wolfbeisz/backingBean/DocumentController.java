package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Checkout;
import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.database.Revision;
import com.wolfbeisz.model.database.Tag;
import com.wolfbeisz.model.web.UpdateDocumentRequest;
import com.wolfbeisz.model.web.ViewDocumentRequest;
import com.wolfbeisz.repository.CheckoutDao;
import com.wolfbeisz.repository.RevisionDao;
import com.wolfbeisz.service.CheckoutService;
import com.wolfbeisz.service.DocumentService;
import com.wolfbeisz.model.web.AddDocumentRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Philipp on 16.12.2014.
 */
@Named
@ViewScoped
public class DocumentController implements Serializable {
    private static final Logger logger = LogManager.getLogger(DocumentController.class);

    private AddDocumentRequest documentInformation = new AddDocumentRequest();
    private ViewDocumentRequest viewDocumentRequest = new ViewDocumentRequest();
    private UpdateDocumentRequest updateDocumentRequest = new UpdateDocumentRequest();

    @Inject
    private transient DocumentService documentService;
    @Inject
    private transient RevisionDao revisionDao;
    @Inject
    private transient CheckoutService checkoutService;

    private Document document;
    private Revision latestRevision;

    @Inject
    private AuthenticationController authenticationController;
    private Checkout checkout;

    public String add() throws IOException{
        Document created = documentService.createDocument(documentInformation);
        return "viewDocument.xhtml?documentid="+created.getId()+"&faces-redirect=true";
    }

    public void loadDocumentBeforeView(){
        document = documentService.findDocument(viewDocumentRequest);
        latestRevision = revisionDao.findLatest(viewDocumentRequest.getDocumentid());
        checkout = checkoutService.findCheckout(authenticationController.getCurrent().getId(), viewDocumentRequest.getDocumentid());
    }

    public void loadDocumentBeforeUpdate() {
        document = documentService.findDocument(updateDocumentRequest.getId());

        //implicit:
        //updateDocumentRequest.setId(document.getId());
        StringBuilder tags = new StringBuilder();
        for (Tag tag : document.getTags()) {
            tags.append(tag.getText());
            tags.append(", ");
        }
        updateDocumentRequest.setTags(tags.toString());
        updateDocumentRequest.setTitle(document.getTitle());
    }

    public void checkout(long revisionId){
        checkout = documentService.checkOutDocument(authenticationController.getCurrent().getId(), revisionId);
    }

    public boolean canCheckout(long revisionId) {
        Revision revision = revisionDao.findById(revisionId);
        logger.debug("canCheckout: revisionId="+revisionId);
        logger.debug(authenticationController.getCurrent().getId());
        logger.debug(revision.getDocument().getId());
        return !documentService.existsCheckout(authenticationController.getCurrent().getId(), revision.getDocument().getId());
    }

    public boolean canCheckin(long revisionId) {
        Revision revision = revisionDao.findById(revisionId);
        return documentService.existsCheckout(authenticationController.getCurrent().getId(), revision.getDocument().getId());
    }

    public String update() {
        Document updatedDocument = documentService.updateDocument(updateDocumentRequest);
        return "viewDocument.xhtml?documentid="+updatedDocument.getId()+"&faces-redirect=true";
    }


    public AddDocumentRequest getDocumentInformation() {
        return documentInformation;
    }

    public void setDocumentInformation(AddDocumentRequest documentInformation) {
        this.documentInformation = documentInformation;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public ViewDocumentRequest getViewDocumentRequest() {
        return viewDocumentRequest;
    }

    public void setViewDocumentRequest(ViewDocumentRequest viewDocumentRequest) {
        this.viewDocumentRequest = viewDocumentRequest;
    }

    public UpdateDocumentRequest getUpdateDocumentRequest() {
        return updateDocumentRequest;
    }

    public void setUpdateDocumentRequest(UpdateDocumentRequest updateDocumentRequest) {
        this.updateDocumentRequest = updateDocumentRequest;
    }

    public Revision getLatestRevision() {
        return latestRevision;
    }

    public void setLatestRevision(Revision latestRevision) {
        this.latestRevision = latestRevision;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }
}
