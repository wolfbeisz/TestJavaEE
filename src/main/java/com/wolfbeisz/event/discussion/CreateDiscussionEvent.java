package com.wolfbeisz.event.discussion;

import com.wolfbeisz.model.database.User;

import java.util.Date;

/**
 * Created by Philipp on 25.02.2015.
 */
public class CreateDiscussionEvent {
    private long documentId;
    private String topic;
    private String firstPost;
    private long userId;
    private Date timestamp;

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFirstPost() {
        return firstPost;
    }

    public void setFirstPost(String firstPost) {
        this.firstPost = firstPost;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CreateDiscussionEvent [documentId="+documentId+",topic="+topic+", firstPost="+firstPost+", userId="+userId+"timestamp="+timestamp+"]";
    }
}
