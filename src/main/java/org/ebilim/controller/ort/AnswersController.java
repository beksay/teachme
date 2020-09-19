package org.ebilim.controller.ort;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.ebilim.conversations.ConversationAnswers;
import org.ebilim.conversations.ConversationQuestions;
import org.ebilim.domain.Answers;
import org.ebilim.service.AnswersService;
import org.ebilim.util.web.Messages;


@Named
@ConversationScoped
public class AnswersController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private AnswersService service;
	@Inject
	private ConversationAnswers conversation;	
	@Inject
	private ConversationQuestions conversationQuestions;	
	
	private Answers answers;
    
	@PostConstruct
	public void init() {
		answers=conversation.getAnswers();
		if (answers==null) answers= new Answers();
	}
	
	public String add() {
		answers = new Answers();
		conversation.setAnswers(answers);
		return form();
	}
	
	public String edit(Answers answers) {
		this.answers = answers;
		conversation.setAnswers(answers);
		return form();
	}
	
	public String save() {
		if(answers == null){
			FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("invalidData"), null) );
			return null;
		}
		answers.setQuestions(conversationQuestions.getQuestions());
		if(answers.getId()==null){
			answers = service.persist(answers);
		}
		else{
			answers = service.merge(answers);
		}
		
		conversation.setAnswers(null);
	    return cancel();  	
	}
	
	public String delete(Answers c) {
		service.remove(c);
		return cancel();
	}
	
	public String cancel() {
		answers = null;
		return list();
	}
	
	private String list() {
		return "/view/ort/answers_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/ort/answers_form.xhtml";
	}

	public Answers getAnswers() {
		return answers;
	}
	
	public void setAnswers(Answers answers) {
		this.answers = answers;
	}

	public ConversationQuestions getConversationQuestions() {
		return conversationQuestions;
	}
	
	public void setConversationQuestions(ConversationQuestions conversationQuestions) {
		this.conversationQuestions = conversationQuestions;
	}
}
