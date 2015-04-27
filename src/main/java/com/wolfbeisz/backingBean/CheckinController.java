package com.wolfbeisz.backingBean;

import com.wolfbeisz.event.CheckinEvent;
import com.wolfbeisz.model.database.Checkout;
import com.wolfbeisz.model.web.ViewDocumentRequest;
import com.wolfbeisz.repository.CheckoutDao;
import com.wolfbeisz.service.DocumentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Philipp on 27.04.2015.
 */
@Named
@ViewScoped
public class CheckinController implements Serializable {
    private static final Logger logger = LogManager.getLogger(CheckinController.class);
    private CheckinEvent checkinEvent = new CheckinEvent();

    @Inject
    private transient CheckoutDao checkoutDao;
    @Inject
    private transient DocumentService documentService;

    private Checkout checkout;

    public void loadCheckout() {
        checkout = checkoutDao.findById(checkinEvent.getCheckoutId());
    }

    public String checkIn() throws IOException {
        documentService.checkIn(checkinEvent);
        return "viewDocument.xhtml?documentid=1&faces-redirect=true";
    }

    public CheckinEvent getCheckinEvent() {
        return checkinEvent;
    }

    public void setCheckinEvent(CheckinEvent checkinEvent) {
        this.checkinEvent = checkinEvent;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }
}
