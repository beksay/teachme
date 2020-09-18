package org.teachme.controller.ort;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.teachme.conversations.ConversationOrtType;
import org.teachme.conversations.ConversationQuestions;
import org.teachme.domain.Questions;
import org.teachme.service.AnswersService;
import org.teachme.service.QuestionsService;
import org.teachme.util.web.Messages;


@Named
@ViewScoped
public class QuestionsController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private QuestionsService service;
	@EJB
	private AnswersService answersService;
	@Inject
	private ConversationQuestions conversation;	
	@Inject
	private ConversationOrtType conversationOrtType;	
	
	private Questions questions;
    
	@PostConstruct
	public void init() {
		questions=conversation.getQuestions();
		if (questions==null) questions= new Questions();
	}
	
	public String add() {
		questions = new Questions();
		conversation.setQuestions(questions);
		return form();
	}
	
	public String edit(Questions questions) {
		this.questions = questions;
		conversation.setQuestions(questions);
		return form();
	}
	
	public String save() {
		if(questions == null){
			FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("invalidData"), null) );
			return null;
		}
		questions.setOrtType(conversationOrtType.getOrtType());
		if(questions.getId()==null){
			questions = service.persist(questions);
		}
		else{
			questions = service.merge(questions);
		}
		
		conversation.setQuestions(null);
	    return cancel();  	
	}

	public void onRowSelect(SelectEvent event) throws IOException {
		questions=(Questions) event.getObject();
		conversation.setQuestions(questions);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/teachme/view/ort/answers_list.xhtml?cid="+conversation.getId());
        
    }
	
	
	public String view(Questions questions) {
		this.questions = questions;
		conversation.setQuestions(questions);
		return answerList();
	}
	
	public String delete(Questions c) {
		service.remove(c);
		return cancel();
	}
	
	public String cancel() {
		questions = null;
		return list();
	}
	
   
	
	private String list() {
		return "/view/ort/questions_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/ort/questions_form.xhtml";
	}
	
	private String answerList() {
		return "/view/ort/answers_list.xhtml?faces-redirect=true";
	}
	
	public String goTest(Questions questions) {
		this.questions = questions;
		conversation.setQuestions(questions);
		return null;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	public ConversationQuestions getConversation() {
		return conversation;
	}
	
	public void setConversation(ConversationQuestions conversation) {
		this.conversation = conversation;
	}
}
