package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.database.Tag;
import com.wolfbeisz.model.web.UpdateDocumentRequest;
import com.wolfbeisz.model.web.ViewDocumentRequest;
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

    private Document document;

    public String add() throws IOException{
        Document created = documentService.createDocument(documentInformation);
        return "viewDocument.xhtml?documentid="+created.getId()+"&faces-redirect=true";
    }

    public void loadDocumentBeforeView(){
        document = documentService.findDocument(viewDocumentRequest);
        //logger.trace("document loaded: "+document.getClass());
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
}
