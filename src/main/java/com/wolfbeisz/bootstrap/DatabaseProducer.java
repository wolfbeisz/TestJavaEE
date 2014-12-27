package com.wolfbeisz.bootstrap;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Philipp on 12.11.2014.
 */

public class DatabaseProducer {


@Produces
@PersistenceContext(unitName = "jpaproject")
private EntityManager em;
}
