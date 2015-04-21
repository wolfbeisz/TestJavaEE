package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.web.DocumentSearchEvent;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * Created by Philipp on 17.02.2015.
 * TODO: should probably be @ViewScoped
 * for pagination see: https://wiki.eclipse.org/EclipseLink/Examples/JPA/Pagination ; be aware of the last update of
 * the page
 */
@Named
@ViewScoped
public class SearchController implements Serializable{
    private List<Document> results;
    private DocumentSearchEvent documentSearchEvent = new DocumentSearchEvent();

    //TODO: if an instance of this class is serialized and later deserialized, this property will be null
    @Inject
    private transient DocumentService documentService;

    @PostConstruct
    public void retrieveAllDocuments()
    {
        results = documentService.findAllDocuments();
    }

    public void backward() {

    }

    public void forward() {}
    public void search() {
        results = documentService.findDocuments(documentSearchEvent);
    }

    public List<Document> getResults() {
        return results;
    }

    public void List(List<Document> results) {
        this.results = results;
    }


    public DocumentSearchEvent getDocumentSearchEvent() {
        return documentSearchEvent;
    }

    public void setDocumentSearchEvent(DocumentSearchEvent documentSearchEvent) {
        this.documentSearchEvent = documentSearchEvent;
    }
}
