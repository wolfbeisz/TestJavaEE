package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.web.AddRevisionRequest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by Philipp on 18.02.2015.
 */
@Named
@RequestScoped
public class UploadRevisionController {
    private AddRevisionRequest addRevisionRequest;

    public void doAddRevision() {}

    public AddRevisionRequest getAddRevisionRequest() {
        return addRevisionRequest;
    }

    public void setAddRevisionRequest(AddRevisionRequest addRevisionRequest) {
        this.addRevisionRequest = addRevisionRequest;
    }
}
