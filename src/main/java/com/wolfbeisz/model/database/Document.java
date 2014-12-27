package com.wolfbeisz.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DOCUMENTS database table.
 * 
 */
@Entity
@Table(name="DOCUMENTS")
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document extends Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=256)
	private String title;

	//bi-directional many-to-one association to Discussion
	@OneToMany(mappedBy="document")
	private List<Discussion> discussions;

	//bi-directional many-to-one association to Revision
	@OneToMany(mappedBy="document")
	private List<Revision> revisions;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="document")
	private List<Tag> tags;

	public Document() {
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Discussion> getDiscussions() {
		return this.discussions;
	}

	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	public Discussion addDiscussion(Discussion discussion) {
		getDiscussions().add(discussion);
		discussion.setDocument(this);

		return discussion;
	}

	public Discussion removeDiscussion(Discussion discussion) {
		getDiscussions().remove(discussion);
		discussion.setDocument(null);

		return discussion;
	}

	public List<Revision> getRevisions() {
		return this.revisions;
	}

	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
	}

	public Revision addRevision(Revision revision) {
		getRevisions().add(revision);
		revision.setDocument(this);

		return revision;
	}

	public Revision removeRevision(Revision revision) {
		getRevisions().remove(revision);
		revision.setDocument(null);

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
		tag.setDocument(this);

		return tag;
	}

	public Tag removeTag(Tag tag) {
		getTags().remove(tag);
		tag.setDocument(null);

		return tag;
	}

}