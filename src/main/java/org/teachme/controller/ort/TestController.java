package org.teachme.controller.ort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.teachme.beans.FilterExample;
import org.teachme.beans.InequalityConstants;
import org.teachme.conversations.ConversationAnswers;
import org.teachme.conversations.ConversationOrtType;
import org.teachme.conversations.ConversationQuestions;
import org.teachme.domain.Answers;
import org.teachme.domain.Feedback;
import org.teachme.domain.MyAnswer;
import org.teachme.domain.Questions;
import org.teachme.enums.SortEnum;
import org.teachme.service.AnswersService;
import org.teachme.service.FeedbackService;
import org.teachme.service.MyAnswerService;
import org.teachme.service.QuestionsService;
import org.teachme.util.web.LoginUtil;


@Named
@ConversationScoped
public class TestController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private QuestionsService service;
	@EJB
	private AnswersService answersService;
	@EJB
	private MyAnswerService myService;
	@EJB
	private FeedbackService feedbackService;
	@Inject
	private ConversationAnswers conversation;	
	@Inject
	private ConversationOrtType conversationOrtType;	
	@Inject
	private ConversationQuestions conversationQuestions;
	@Inject
	private LoginUtil loginUtil;
	
	private Answers answers;
	private MyAnswer myAnswer;
    private Feedback feedback;
    
	@PostConstruct
	public void init() {
		answers=conversation.getAnswers();
		if (answers==null) answers= new Answers();
		if (myAnswer==null) myAnswer= new MyAnswer();
		if (feedback==null) feedback= new Feedback();
	}

	public void sendToConfirm() {
		if(answers==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select the answer!","Select the answer!"));
			return;
		}
		myAnswer.setAnswers(answers);
		myAnswer.setQuestions(answers.getQuestions());
		myAnswer.setUser(loginUtil.getCurrentUser());
		myService.persist(myAnswer);
	}
	
	public void sendFeedback() {
		if(feedback==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select the feedback!","Select the feedback!"));
			return;
		}
		feedback.setQuestions(conversationQuestions.getQuestions());
		feedback.setUser(loginUtil.getCurrentUser());
		feedback.setDate(new Date());
		feedbackService.persist(feedback);
		
		feedback = new Feedback();
	}
	
	public String getBoldDocuments(Answers answers) {
		
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("answers", answers, InequalityConstants.EQUAL));
        examples.add(new FilterExample("user", loginUtil.getCurrentUser(), InequalityConstants.EQUAL));
        examples.add(new FilterExample("answers.answer",true , InequalityConstants.EQUAL));
        List<FilterExample> examples2=new ArrayList<>();
        examples2.add(new FilterExample("answers", answers, InequalityConstants.EQUAL));
        examples2.add(new FilterExample("user", loginUtil.getCurrentUser(), InequalityConstants.EQUAL));
        examples2.add(new FilterExample("answers.answer",false , InequalityConstants.EQUAL));
        
        Long c = myService.countByExample(examples);
        Long c2 = myService.countByExample(examples2);
        if (c > 0) {
        	System.out.println("===GREEEN");
        	return "GREENDOC";
		}else if (c2 > 0) {
        	System.out.println("===RED");
        	return "REDDOC";
		}else {
			System.out.println("===OTHERCOLOR");
			return "";
		}
	}
	
   public String getBoldQuestions(Questions questions) {
		
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("questions", questions, InequalityConstants.EQUAL));
        examples.add(new FilterExample("user", loginUtil.getCurrentUser(), InequalityConstants.EQUAL));
        examples.add(new FilterExample("answers.answer",true , InequalityConstants.EQUAL));
        List<FilterExample> examples2=new ArrayList<>();
        examples2.add(new FilterExample("questions", questions, InequalityConstants.EQUAL));
        examples2.add(new FilterExample("user", loginUtil.getCurrentUser(), InequalityConstants.EQUAL));
        examples2.add(new FilterExample("answers.answer",false , InequalityConstants.EQUAL));
        
        Long c = myService.countByExample(examples);
        Long c2 = myService.countByExample(examples2);
        if (c > 0) {
        	System.out.println("===GREEEN");
        	return "GREENMENU";
		}else if (c2 > 0) {
        	System.out.println("===RED");
        	return "REDMENU";
		}else {
			System.out.println("===OTHERCOLOR");
			return "ELSEMENU";
		}
	}
	
	public Boolean isTestEnded(Questions questions) {
		List<FilterExample> examples=new ArrayList<>();
        if (questions!=null) examples.add(new FilterExample("questions", questions, InequalityConstants.EQUAL));
        List<MyAnswer> c = myService.findByExample(0, 10, examples);
        if (c.size()>0) {
        	return true;
		}else {
			return false;
		}	
	}
	
	public Boolean isTestEnded2(Questions questions) {
		List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("questions", questions, InequalityConstants.EQUAL));
        List<MyAnswer> c = myService.findByExample(0, 10, examples);
        if (c.size()>0) {
        	return false;
		}else {
			return true;
		}	
	}
	

	public List<Questions> getQuestionsList() {

		List<FilterExample> examples = new ArrayList<>();
	
		examples.add(new FilterExample("ortType", conversationOrtType.getOrtType(), InequalityConstants.EQUAL));	
		
		return service.findByExample(0, 1000, SortEnum.ASCENDING, examples, "number");
	}
   
   public List<Questions> getQuestionsTestList() {

		List<FilterExample> examples = new ArrayList<>();
	
		examples.add(new FilterExample("ortType", conversationOrtType.getOrtType(), InequalityConstants.EQUAL));	
		
		return service.findByExample(0, 1, SortEnum.ASCENDING, examples, "number");
   }
   
   public List<Answers> getAnswersList(Questions questions) {

		List<FilterExample> examples = new ArrayList<>();
	
		examples.add(new FilterExample("questions", questions, InequalityConstants.EQUAL));	
		
		return answersService.findByExample(0, 10, examples);
	}
	
   public List<Feedback> getFeedbackList(Questions questions) {

		List<FilterExample> examples = new ArrayList<>();
	
		examples.add(new FilterExample("questions", questions, InequalityConstants.EQUAL));	
		
		return feedbackService.findByExample(0, 100, SortEnum.DESCENDING, examples, "date");
	}
   
	public ConversationQuestions getConversationQuestions() {
		return conversationQuestions;
	}

	public void setConversationQuestions(ConversationQuestions conversationQuestions) {
		this.conversationQuestions = conversationQuestions;
	}

	public Answers getAnswers() {
		return answers;
	}

	public void setAnswers(Answers answers) {
		this.answers = answers;
	}

	public MyAnswer getMyAnswer() {
		return myAnswer;
	}

	public void setMyAnswer(MyAnswer myAnswer) {
		this.myAnswer = myAnswer;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

}
