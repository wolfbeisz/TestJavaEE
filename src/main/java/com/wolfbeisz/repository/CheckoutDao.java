package com.wolfbeisz.repository;

import com.wolfbeisz.model.database.Checkout;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Philipp on 27.04.2015.
 */

@Transactional(Transactional.TxType.SUPPORTS)
public class CheckoutDao {
    @Inject
    private EntityManager em;


    public void create(Checkout checkout) {
        em.persist(checkout);
    }


    public void delete(Checkout checkout) {
        em.remove(checkout);
    }

    public List<Checkout> findByUserAndDocument(long userId, long documentId) {
        TypedQuery<Checkout> namedQuery = em.createNamedQuery("Checkout.findByUserAndDocument", Checkout.class);
        namedQuery.setParameter("userId", userId);
        namedQuery.setParameter("documentId", documentId);
        return namedQuery.getResultList();
    }

    public Checkout findById(long checkoutId) {
        return em.find(Checkout.class, checkoutId);
    }

}
