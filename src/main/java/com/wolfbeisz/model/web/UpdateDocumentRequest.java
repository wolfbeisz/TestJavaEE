package com.wolfbeisz.model.web;

/**
 * Created by Philipp on 16.02.2015.
 */
public class UpdateDocumentRequest {
    private long id;
    private String title;
    private String tags;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
