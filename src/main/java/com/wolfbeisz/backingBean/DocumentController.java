package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.web.UpdateDocumentRequest;
import com.wolfbeisz.model.web.ViewDocumentRequest;
import com.wolfbeisz.service.DocumentService;
import com.wolfbeisz.model.web.AddDocumentRequest;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;


/**
 * Created by Philipp on 16.12.2014.
 */
@Named
@RequestScoped
public class DocumentController {
    private AddDocumentRequest documentInformation = new AddDocumentRequest();
    private ViewDocumentRequest viewDocumentRequest = new ViewDocumentRequest();
    private UpdateDocumentRequest updateDocumentRequest = new UpdateDocumentRequest();


    @Inject
    private DocumentService documentService;

    private Document document;

    public String add() throws IOException{
        documentService.createDocument(documentInformation);
        return "createDocument.xhtml";
    }

    public void loadDocumentBeforeView(){
       document = documentService.findDocument(viewDocumentRequest.getId());
    }

    public void loadDocumentBeforeUpdate() {
        document = documentService.findDocument(updateDocumentRequest.getId());
        updateDocumentRequest.setTitle(document.getTitle());
    }

    public void checkin() {

    }
    public void checkout(){}

    public void update() {}


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
}
