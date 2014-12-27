package com.wolfbeisz.model.web;

import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

/**
 * Created by Philipp on 16.12.2014.
 */
public class AddDocumentRequest {
    @NotNull
    private Part file;

    @NotNull
    private String title;



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
}
