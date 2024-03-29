package org.ebilim.controller.ort;

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

import org.ebilim.beans.FilterExample;
import org.ebilim.beans.InequalityConstants;
import org.ebilim.conversations.ConversationAnswers;
import org.ebilim.conversations.ConversationOrtType;
import org.ebilim.conversations.ConversationQuestions;
import org.ebilim.domain.Answers;
import org.ebilim.domain.Feedback;
import org.ebilim.domain.FeedbackDetail;
import org.ebilim.domain.MyAnswer;
import org.ebilim.domain.Questions;
import org.ebilim.enums.SortEnum;
import org.ebilim.service.AnswersService;
import org.ebilim.service.FeedbackDetailService;
import org.ebilim.service.FeedbackService;
import org.ebilim.service.MyAnswerService;
import org.ebilim.service.QuestionsService;
import org.ebilim.util.web.LoginUtil;


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
	@EJB
	private FeedbackDetailService feedbackDetailService;
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
   
   public List<FeedbackDetail> getFeedbackDetailList(Feedback feedback) {

		List<FilterExample> examples = new ArrayList<>();
	
		examples.add(new FilterExample("feedback", feedback, InequalityConstants.EQUAL));	
		
		return feedbackDetailService.findByExample(0, 100, SortEnum.DESCENDING, examples, "date");
	}
   
    public Boolean checkDetail(Feedback feedback) {
    	List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("feedback", feedback, InequalityConstants.EQUAL));	
		List<FeedbackDetail> details = feedbackDetailService.findByExample(0, 10, examples);
     	if (details.size()>0) {
			return true;
		} else {
            return false;
		}
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
