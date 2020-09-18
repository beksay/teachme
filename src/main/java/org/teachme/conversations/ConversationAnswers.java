package org.teachme.conversations;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.teachme.annotation.Logged;
import org.teachme.domain.Answers;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationAnswers extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Answers answers;
	
	public Answers getAnswers() {
		return answers;
	}

	public void setAnswers(Answers answers) {
		this.answers = answers;
	}

	
}
