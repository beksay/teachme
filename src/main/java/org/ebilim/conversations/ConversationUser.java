package org.ebilim.conversations;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.ebilim.annotation.Logged;
import org.ebilim.domain.Person;
import org.ebilim.domain.User;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationUser extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private User user;
	private Person person;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}

	
}
