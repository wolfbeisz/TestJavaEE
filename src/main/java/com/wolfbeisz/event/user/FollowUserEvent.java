package com.wolfbeisz.event.user;

/**
 * Created by Philipp on 23.02.2015.
 */
public class FollowUserEvent {
    // the one who follows another user
    private long userId;

    //the user who is followed by the first user
    private long idolId;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getIdolId() {
        return idolId;
    }

    public void setIdolId(long idolId) {
        this.idolId = idolId;
    }
}
