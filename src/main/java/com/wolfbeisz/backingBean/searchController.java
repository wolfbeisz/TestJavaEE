package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.qualifiers.Example;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;


/**
 * Created by Philipp on 17.02.2015.
 * TODO: should probably be @ViewScoped
 * for pagination see: https://wiki.eclipse.org/EclipseLink/Examples/JPA/Pagination ; be aware of the last update of
 * the page
 */
@Named
@RequestScoped
public class SearchController {
    @Inject @Example
    private Collection<Document> results;
    private String term;

    public void backward() {

    }

    public void forward() {}
    public void search() {}

    public Collection<Document> getResults() {
        return results;
    }

    public void setResults(Collection<Document> results) {
        this.results = results;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
