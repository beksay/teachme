package org.ebilim.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "my_answer")
public class MyAnswer extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;
    private Answers answers;
    private Questions questions;
    private User user;
	
	@ManyToOne
    @JoinColumn(name="answers_id")
	public Answers getAnswers() {
		return answers;
	}
	
	public void setAnswers(Answers answers) {
		this.answers = answers;
	}

	@ManyToOne
    @JoinColumn(name="questions_id")
	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	@ManyToOne
    @JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
