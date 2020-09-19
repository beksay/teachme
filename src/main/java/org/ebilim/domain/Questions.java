package org.ebilim.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "questions")
public class Questions extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;
    private OrtType ortType;
    private String title;
    private Integer number;

	@Column(name = "title",length = 5000)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne
    @JoinColumn(name="ort_type_id")
	public OrtType getOrtType() {
		return ortType;
	}
	
	public void setOrtType(OrtType ortType) {
		this.ortType = ortType;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
