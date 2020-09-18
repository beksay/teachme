package org.teachme.controller.ort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;
import org.teachme.beans.FilterExample;
import org.teachme.beans.InequalityConstants;
import org.teachme.conversations.ConversationQuestions;
import org.teachme.domain.Answers;
import org.teachme.model.AnswersModel;
import org.teachme.service.AnswersService;

@Named
@ViewScoped
public class AnswersList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	private AnswersModel model; 
	private Answers Answers;
	private Integer first;
	
	@EJB
	private AnswersService service;
	@Inject
	private ConversationQuestions conversationQuestions;

	public AnswersList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("questions", conversationQuestions.getQuestions(), InequalityConstants.EQUAL));
		model = new AnswersModel(filters, service);
		
	}
	
	public AnswersModel getModel() {
		return model;
	}
	
	public void setModel(AnswersModel model) {
		this.model = model;
	}
	
	public Integer getFirst() {
		return first;
	}
	
	public void setFirst(Integer first) {
		this.first = first;
	}
	
	public void saveState() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("model", model);
		session.setAttribute("first", first);
	}
	
	public void restoreState() {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		model = (AnswersModel) session.getAttribute("model");
		first = (Integer) session.getAttribute("first");
	}
	
	public void removeState() {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("model", null);
		session.setAttribute("first", null);
		
		model = null;
		first = null;
	}
	
	public void onPageChange(PageEvent event) {  
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		setFirst(((DataTable) event.getSource()).getRows() * event.getPage());
		session.setAttribute("first", first);
	}

	public Answers getAnswers() {
		return Answers;
	}

	public void setAnswers(Answers Answers) {
		this.Answers = Answers;
	} 
}
