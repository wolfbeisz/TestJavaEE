package com.wolfbeisz.bootstrap;

import com.wolfbeisz.model.database.*;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.repository.DocumentDAO;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Philipp on 16.12.2014.
 */

public class ExampleDataProducer {

    @Inject
    private EntityManager entityManager;
    @Inject
    private DocumentDAO documentDAO;

    @Produces @Example
    public User findUser()
    {
        TypedQuery<User> userTypedQuery = entityManager.createNamedQuery("User.findByEmail", User.class);
        userTypedQuery.setParameter("email", "philipp@wolfbeisz.com");
        return userTypedQuery.getSingleResult();
    }

    @Produces @Example
    public Collection<Activity> createActivities()
    {
        List<Activity> activities = new ArrayList<Activity>();
        activities.add(new Comment());
        activities.get(0).setCreatedBy(findUser());
        activities.get(0).setCreatedStamp(new Date());

        activities.add(new Document());
        activities.get(1).setCreatedBy(findUser());
        activities.get(1).setCreatedStamp(new Date());

        return activities;
    }

    @Produces @Example
    public Collection<Revision> createRevisions() {
        List<Revision> revisions = new ArrayList<Revision>();
        Revision first = new Revision();
        first.setId(1);
        first.setMimetype("text/plain");
        first.setVersion(new BigDecimal(1));

        Revision second = new Revision();
        second.setId(2);
        second.setMimetype("text/plain");
        second.setVersion(new BigDecimal(2));


        revisions.add(first);
        revisions.add(second);
        return revisions;
    }

    @Produces @Example
    public Collection<Document> allDocuments() {
        return documentDAO.findAllDocuments();
    }
}
