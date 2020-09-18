package org.teachme.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ort_title")
public class OrtTitle extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;
    private String name;
    private BigDecimal amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
