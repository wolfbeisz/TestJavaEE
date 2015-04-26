package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Activity;
import com.wolfbeisz.model.database.User;
import com.wolfbeisz.qualifiers.Authenticated;
import com.wolfbeisz.qualifiers.Example;
import com.wolfbeisz.repository.ActivityDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Philipp on 17.02.2015.
 */
@Named
@RequestScoped
public class DashboardController /*implements Serializable*/ {
    private static final Logger logger = LogManager.getLogger(DashboardController.class);
    private Collection<Activity> activities = new ArrayList<Activity>();

    @Inject
    private ActivityDao activityDao;

    @Inject @Authenticated
    private User authenticatedUser;

    public void loadActivities() {
        activities = activityDao.findActivitiesOfIdols(authenticatedUser.getId());
    }

    public Collection<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Collection<Activity> activities) {
        this.activities = activities;
    }
}
