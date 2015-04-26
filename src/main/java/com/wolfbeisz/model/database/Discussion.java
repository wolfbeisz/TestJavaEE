package com.wolfbeisz.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the DISCUSSIONS database table.
 * 
 */
@Entity
@Table(name="DISCUSSIONS")
@NamedQueries({
    @NamedQuery(name="Discussion.findAll", query="SELECT d FROM Discussion d"),
    @NamedQuery(name="Discussion.findByDocumentId", query = "SELECT d from Discussion d where d.document.id = :documentId")
})
public class Discussion extends Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="discussion")
	private List<Comment> comments;

	//bi-directional many-to-one association to Document
	@ManyToOne
	@JoinColumn(name="DOCUMENTID", nullable=false)
	private Document document;

    @Column(nullable = false)
    private String topic;

	public Discussion() {
        comments = new ArrayList<Comment>();
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setDiscussion(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setDiscussion(null);

		return comment;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}