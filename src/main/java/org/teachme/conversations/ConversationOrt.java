package org.teachme.conversations;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.teachme.annotation.Logged;
import org.teachme.domain.Ort;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationOrt extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Ort ort;

	public Ort getOrt() {
		return ort;
	}

	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	
}
