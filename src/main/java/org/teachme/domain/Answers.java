package org.teachme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "answers")
public class Answers extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;
    private Questions questions;
    private String letter;
    private String title;
    private Boolean answer;

	@Column(name = "title",length = 5000)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Boolean getAnswer() {
		return answer;
	}
	
	public void setAnswer(Boolean answer) {
		this.answer = answer;
	}
	
	@ManyToOne
    @JoinColumn(name="questions_id")
	public Questions getQuestions() {
		return questions;
	}
	
	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	@Column(name = "letter",length = 2)
	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}
}
