package com.wolfbeisz.service;

import com.wolfbeisz.model.database.Checkout;
import com.wolfbeisz.repository.CheckoutDao;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Philipp on 27.04.2015.
 */
public class CheckoutService {
    @Inject
    private CheckoutDao checkoutDao;

    public Checkout findCheckout(long userId, long documentId) {
        List<Checkout> checkouts = checkoutDao.findByUserAndDocument(userId, documentId);
        if (checkouts.size() > 1) {
            throw new IllegalStateException("a user is allowed to checkout only one document");
        }
        if (checkouts.size() == 1) {
            return checkouts.get(0);
        }
        return null;
    }
}
