package com.wolfbeisz.backingBean;

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
public class AddDocument {
    private AddDocumentRequest documentInformation = new AddDocumentRequest();

    @Inject
    private DocumentService documentService;

    public String add() throws IOException{
        documentService.createDocument(documentInformation);
        return "hello.xhtml";
    }


    public AddDocumentRequest getDocumentInformation() {
        return documentInformation;
    }

    public void setDocumentInformation(AddDocumentRequest documentInformation) {
        this.documentInformation = documentInformation;
    }
}
