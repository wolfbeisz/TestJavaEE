package com.wolfbeisz.model.database;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Philipp on 26.12.2014.
 */
//TODO: add named queries (especially for querying all activities) -> activity stream
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Activity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false, precision=14)
    private long id;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name="CREATED_BY", nullable=false)
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_STAMP", nullable=false)
    private Date createdStamp;


    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(Date createdStamp) {
        this.createdStamp = createdStamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
