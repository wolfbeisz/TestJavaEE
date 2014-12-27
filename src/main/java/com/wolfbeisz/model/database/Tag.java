package com.wolfbeisz.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TAGS database table.
 * 
 */
@Entity
@Table(name="TAGS")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag extends Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=256)
	private String text;

	//bi-directional many-to-one association to Document
	@ManyToOne
	@JoinColumn(name="DOCUMENTID", nullable=false)
	private Document document;

	public Tag() {
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
}