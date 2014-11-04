package com.wolfbeisz;

import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 * Created by Philipp on 04.11.2014.
 */
@Named
@Stateless
public class ExampleBackingBean {
    public String getGreeting() {
        return "Hello";
    }
}
