package com.wolfbeisz.service;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by Philipp on 14.12.2014.
 */
@Named
@Stateless
public class ExampleEJB {

    public String getGreeting() {
        return "Hello";
    }
}
