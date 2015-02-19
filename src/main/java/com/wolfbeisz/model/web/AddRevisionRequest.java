package com.wolfbeisz.model.web;

import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

/**
 * Created by Philipp on 18.02.2015.
 */
public class AddRevisionRequest {
    @NotNull
    private Part file;

    private Long documentid;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Long getDocumentid() {
        return documentid;
    }

    public void setDocumentid(Long documentid) {
        this.documentid = documentid;
    }
}
