package org.teachme.conversations;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.teachme.annotation.Logged;
import org.teachme.domain.Questions;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationQuestions extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Questions questions;

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	
}
