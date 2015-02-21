package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.database.Tag;
import com.wolfbeisz.model.web.UpdateDocumentRequest;
import com.wolfbeisz.model.web.ViewDocumentRequest;
import com.wolfbeisz.service.DocumentService;
import com.wolfbeisz.model.web.AddDocumentRequest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;


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

    //TODO: this is a hack: actually a redirect to viewDocument.xhtml?documentid=#{created.getId()} would be right
    public String add() throws IOException{
        return "viewDocument.xhtml?documentid=1?faces-redirect=true";
        /*Document created = documentService.createDocument(documentInformation);
        document = created;
        return "viewDocument.xhtml";*/
    }

    //TODO: this is a hack: there should be no condition
    public void loadDocumentBeforeView(){
       if (document == null) {
           document = documentService.findDocument(viewDocumentRequest);
       }
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
