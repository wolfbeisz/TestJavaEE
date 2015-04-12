package com.wolfbeisz.event;

/**
 * Created by Philipp on 10.04.2015.
 */
public class AddCommentEvent {
    private long userId;
    private long discussionId;

    public long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(long discussionId) {
        this.discussionId = discussionId;
    }

    private String commentText;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
