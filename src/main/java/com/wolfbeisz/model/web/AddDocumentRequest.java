package com.wolfbeisz.model.web;

import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Philipp on 16.12.2014.
 */
public class AddDocumentRequest {
    @NotNull
    private Part file;

    @NotNull @Size(min = 3)
    private String title;

    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
