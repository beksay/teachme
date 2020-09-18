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
import org.teachme.conversations.ConversationOrtType;
import org.teachme.domain.Questions;
import org.teachme.model.QuestionsModel;
import org.teachme.service.QuestionsService;

@Named
@ViewScoped
public class QuestionsList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	private QuestionsModel model; 
	private Questions Questions;
	private Integer first;
	private String searchString;
	
	@EJB
	private QuestionsService service;
	@Inject
	private ConversationOrtType conversationOrtType;

	public QuestionsList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("ortType", conversationOrtType.getOrtType(), InequalityConstants.EQUAL));
		if (searchString != null && searchString.length()>0) {
			filters.add(new FilterExample(true, "title", '%' + searchString + '%', InequalityConstants.LIKE, true));
		}
		model = new QuestionsModel(filters, service);
		
	}
	
	public QuestionsModel getModel() {
		return model;
	}
	
	public void setModel(QuestionsModel model) {
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
		model = (QuestionsModel) session.getAttribute("model");
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


	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Questions getQuestions() {
		return Questions;
	}

	public void setQuestions(Questions Questions) {
		this.Questions = Questions;
	} 
}
