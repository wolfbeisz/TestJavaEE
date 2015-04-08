package com.wolfbeisz.model.database;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philipp on 28.02.2015.
 */
@Entity
@Table(name="GROUPS")
@NamedQueries(
        @NamedQuery(name="Group.findByName", query = "SELECT g FROM Group g WHERE g.name = :name")
)
public class Group {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;

    public Group() {
        users = new ArrayList<User>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
