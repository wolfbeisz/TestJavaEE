package com.wolfbeisz.bootstrap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Philipp on 12.11.2014.
 */

public class DatabaseProducer {
    private static final Logger logger = LogManager.getLogger(com.wolfbeisz.bootstrap.DatabaseProducer.class);

//@Produces
@PersistenceContext(unitName = "jpaproject")
private EntityManager em;

    @Produces
    public EntityManager getEm() {
        logger.debug("produce entityManager: "+em.toString());
        return em;
    }
}
