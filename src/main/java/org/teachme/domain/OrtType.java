package org.teachme.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ort_type")
public class OrtType extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;
    private Ort ort;
    private OrtTitle ortTitle;
	
	@ManyToOne
    @JoinColumn(name="ort_id")
	public Ort getOrt() {
		return ort;
	}
	
	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	@ManyToOne
    @JoinColumn(name="ort_title_id")
	public OrtTitle getOrtTitle() {
		return ortTitle;
	}

	public void setOrtTitle(OrtTitle ortTitle) {
		this.ortTitle = ortTitle;
	}

}
