package com.wolfbeisz.backingBean;

import com.wolfbeisz.model.database.Activity;
import com.wolfbeisz.qualifiers.Example;

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
    private Collection<Activity> activities = new ArrayList<Activity>();

    public void loadActivities() {    }

    public Collection<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Collection<Activity> activities) {
        this.activities = activities;
    }
}
