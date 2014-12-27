package com.wolfbeisz.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the REVISIONS database table.
 * 
 */
@Entity
@Table(name="REVISIONS")
@NamedQuery(name="Revision.findAll", query="SELECT r FROM Revision r")
public class Revision extends Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	@Column(nullable=false)
	private byte[] filecontent;

	@Column(length=256)
	private String mimetype;

	@Column(name="VERSION", nullable=false, precision=14)
	private BigDecimal version;

	//bi-directional many-to-one association to Checkout
	@OneToMany(mappedBy="revision")
	private List<Checkout> checkouts;

	//bi-directional many-to-one association to Document
	@ManyToOne
	@JoinColumn(name="DOCUMENTID", nullable=false)
	private Document document;

	public Revision() {
	}

	public byte[] getFilecontent() {
		return this.filecontent;
	}

	public void setFilecontent(byte[] filecontent) {
		this.filecontent = filecontent;
	}

	public String getMimetype() {
		return this.mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public List<Checkout> getCheckouts() {
		return this.checkouts;
	}

	public void setCheckouts(List<Checkout> checkouts) {
		this.checkouts = checkouts;
	}

	public Checkout addCheckout(Checkout checkout) {
		getCheckouts().add(checkout);
		checkout.setRevision(this);

		return checkout;
	}

	public Checkout removeCheckout(Checkout checkout) {
		getCheckouts().remove(checkout);
		checkout.setRevision(null);

		return checkout;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}