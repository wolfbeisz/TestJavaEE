package com.wolfbeisz.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CHECKOUTS database table.
 * 
 */
@Entity
@Table(name="CHECKOUTS")
@NamedQuery(name="Checkout.findAll", query="SELECT c FROM Checkout c")
public class Checkout extends Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Revision
	@ManyToOne
	@JoinColumn(name="REVISIONID", nullable=false)
	private Revision revision;

	public Checkout() {
	}

	public Revision getRevision() {
		return this.revision;
	}

	public void setRevision(Revision revision) {
		this.revision = revision;
	}
}