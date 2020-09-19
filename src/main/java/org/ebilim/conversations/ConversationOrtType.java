package org.ebilim.conversations;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.ebilim.annotation.Logged;
import org.ebilim.domain.OrtType;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationOrtType extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private OrtType ortType;

	public OrtType getOrtType() {
		return ortType;
	}

	public void setOrtType(OrtType ortType) {
		this.ortType = ortType;
	}

	
}
