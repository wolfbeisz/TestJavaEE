package com.wolfbeisz.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQueries(value={
        @NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
        @NamedQuery(name="User.findByEmail", query="select u from User u where u.email = :email"),
        @NamedQuery(name="User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
        @NamedQuery(name="User.findByApproximateName", query = "SELECT u FROM User u WHERE u.name LIKE :namePattern")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, precision=14)
	private long id;

	@Column(length=256)
	private String description;

	@Column(unique = true, nullable=false, length=256)
	private String email;

	@Column(length=256)
	private String location;

	@Column(nullable=false, length=256)
	private String name;

	@Column(nullable=false, length=256)
	private String password;

	@Column(length=256)
	private String phone;

	@Lob
	@Column(name="PROFILE_IMAGE")
	private byte[] profileImage;

	//bi-directional many-to-one association to Checkout
	@OneToMany(mappedBy="createdBy")
	private List<Checkout> checkouts;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="createdBy")
	private List<Comment> comments;

	//bi-directional many-to-one association to Discussion
	@OneToMany(mappedBy="createdBy")
	private List<Discussion> discussions;

	//bi-directional many-to-one association to Document
	@OneToMany(mappedBy="createdBy")
	private List<Document> documents;

	//bi-directional many-to-one association to Revision
	@OneToMany(mappedBy="createdBy")
	private List<Revision> revisions;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="createdBy")
	private List<Tag> tags;
/*
	//bi-directional many-to-one association to UserFollowsUser
	@OneToMany(mappedBy="user1")
	private List<UserFollowsUser> userFollowsUsers1;

	//bi-directional many-to-one association to UserFollowsUser
	@OneToMany(mappedBy="user2")
	private List<UserFollowsUser> userFollowsUsers2;*/

    @ManyToMany(mappedBy = "followers", fetch = FetchType.EAGER)
    private List<User> idols;

    @ManyToMany
    @JoinTable(name = "USER_FOLLOWS_USER",
        joinColumns = @JoinColumn(name="USERID"),
        inverseJoinColumns = @JoinColumn(name="FOLLOWERID")
    )
    private List<User> followers;

    @ManyToMany
    @JoinTable(name = "USER_IN_GROUP",
            joinColumns = @JoinColumn(name = "USER_EMAIL", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_NAME", referencedColumnName = "name")
    )
    private List<Group> groups;

	public User() {
        checkouts = new ArrayList<Checkout>();
        comments = new ArrayList<Comment>();
        discussions = new ArrayList<Discussion>();
        documents = new ArrayList<Document>();
        revisions = new ArrayList<Revision>();
        tags = new ArrayList<Tag>();
        idols = new ArrayList<User>();
        followers = new ArrayList<User>();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getProfileImage() {
		return this.profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public List<Checkout> getCheckouts() {
		return this.checkouts;
	}

	public void setCheckouts(List<Checkout> checkouts) {
		this.checkouts = checkouts;
	}

	public Checkout addCheckout(Checkout checkout) {
		getCheckouts().add(checkout);
		checkout.setCreatedBy(this);

		return checkout;
	}

	public Checkout removeCheckout(Checkout checkout) {
		getCheckouts().remove(checkout);
		checkout.setCreatedBy(null);

		return checkout;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setCreatedBy(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setCreatedBy(null);

		return comment;
	}

	public List<Discussion> getDiscussions() {
		return this.discussions;
	}

	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	public Discussion addDiscussion(Discussion discussion) {
		getDiscussions().add(discussion);
		discussion.setCreatedBy(this);

		return discussion;
	}

	public Discussion removeDiscussion(Discussion discussion) {
		getDiscussions().remove(discussion);
		discussion.setCreatedBy(null);

		return discussion;
	}

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setCreatedBy(this);

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setCreatedBy(null);

		return document;
	}

	public List<Revision> getRevisions() {
		return this.revisions;
	}

	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
	}

	public Revision addRevision(Revision revision) {
		getRevisions().add(revision);
		revision.setCreatedBy(this);

		return revision;
	}

	public Revision removeRevision(Revision revision) {
		getRevisions().remove(revision);
		revision.setCreatedBy(null);

		return revision;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Tag addTag(Tag tag) {
		getTags().add(tag);
		tag.setCreatedBy(this);

		return tag;
	}

	public Tag removeTag(Tag tag) {
		getTags().remove(tag);
		tag.setCreatedBy(null);

		return tag;
	}


    public List<User> getIdols() {
        return idols;
    }

    public void setIdols(List<User> idols) {
        this.idols = idols;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public User addFollower(User user) {
        getFollowers().add(user);
        user.getIdols().add(this);

        return user;
    }

    public User addIdol(User user) {
        getIdols().add(user);
        user.getFollowers().add(this);

        return user;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Group addGroup(Group group) {
        getGroups().add(group);
        group.getUsers().add(this);

        return group;
    }

    public Group removeGroup(Group group) {
        getGroups().remove(group);
        group.getUsers().remove(this);

        return group;
    }
}
