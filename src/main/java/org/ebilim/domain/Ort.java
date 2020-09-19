package org.ebilim.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ort")
public class Ort extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;
    private Integer year;
    private String title;
	
	@Column(name = "title",length = 5000)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
