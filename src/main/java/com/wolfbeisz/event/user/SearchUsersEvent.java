package com.wolfbeisz.event.user;

/**
 * Created by Philipp on 21.02.2015.
 */
public class SearchUsersEvent {
    private String term;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
