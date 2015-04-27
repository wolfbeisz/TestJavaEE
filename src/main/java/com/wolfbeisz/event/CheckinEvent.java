package com.wolfbeisz.event;

import com.wolfbeisz.model.web.AddRevisionRequest;

import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

/**
 * Created by Philipp on 27.04.2015.
 */
public class CheckinEvent {
    @NotNull
    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    @NotNull
    private long checkoutId;

    public long getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(long checkoutId) {
        this.checkoutId = checkoutId;
    }
}
