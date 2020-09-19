package org.ebilim.controller.publics;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.ebilim.conversations.Conversational;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Named
@ConversationScoped
public class PublicController extends Conversational {
	
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init() {
	
	}
	
	
}
