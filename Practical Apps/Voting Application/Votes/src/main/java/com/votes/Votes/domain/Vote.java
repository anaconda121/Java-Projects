package com.votes.Votes.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Vote {
    private VoteId pk;
    private boolean upvote;

    @EmbeddedId
    public VoteId getPk() {
        return this.pk;
    }

    public void setPk(VoteId pk) {
        this.pk = pk;
    }


    public boolean isUpvote() {
        return this.upvote;
    }

    public void setUpvote(boolean upvote) {
        this.upvote = upvote;
    }

}