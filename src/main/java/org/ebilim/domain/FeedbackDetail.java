package org.ebilim.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "feedback_detail")
public class FeedbackDetail extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;
    private Feedback feedback;
    private User user;
    private String title;
    private Date date;
	
	@Column(name = "title",length = 2000)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne
    @JoinColumn(name="feedback_id")
	public Feedback getFeedback() {
		return feedback;
	}
	
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@ManyToOne
    @JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
