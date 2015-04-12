package com.wolfbeisz.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the COMMENTS database table.
 * 
 */
@Entity
@Table(name="COMMENTS")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment extends Activity implements Serializable {
	private static final long serialVersionUID = 1L;

//	@Column(name="POSITION", nullable=false, precision=6)
//	private BigDecimal position;

	@Column(nullable=false, length=4000)
	private String text;

    /*
	//bi-directional many-to-one association to Comment
    //parent comment: the comment to which this one relates to
	@ManyToOne
	@JoinColumn(name="RELATES_TO")
	private Comment comment;

	//bi-directional many-to-one association to Comment
    //child comments : the comments which relate to this one
	@OneToMany(mappedBy="comment")
	private List<Comment> comments;
    */

	//bi-directional many-to-one association to Discussion
	@ManyToOne
	@JoinColumn(name="DISCUSSIONID", nullable=false)
	private Discussion discussion;

	public Comment() {
        //comments = new ArrayList<Comment>();
	}

    /*
	public BigDecimal getPosition() {
		return this.position;
	}

	public void setPosition(BigDecimal position) {
		this.position = position;
	}*/

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

    /*
	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setComment(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setComment(null);

		return comment;
	}
    */
	public Discussion getDiscussion() {
		return this.discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}
}