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
import org.teachme.conversations.ConversationOrt;
import org.teachme.domain.OrtType;
import org.teachme.model.OrtTypeModel;
import org.teachme.service.OrtTypeService;

@Named
@ViewScoped
public class OrtTypeList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	private OrtTypeModel model; 
	private OrtType OrtType;
	private Integer first;
	private String searchString;
	
	@EJB
	private OrtTypeService service;
	@Inject
	private ConversationOrt conversationOrt;

	public OrtTypeList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){
		List<FilterExample> filters = new ArrayList<>();
		if(conversationOrt !=null && conversationOrt.getOrt() !=null) {
			filters.add(new FilterExample("ort", conversationOrt.getOrt(), InequalityConstants.EQUAL));
		}else {
			filters.add(new FilterExample("id",InequalityConstants.IS_NULL_SINGLE));
		}
		
		if (searchString != null && searchString.length()>0) {
			filters.add(new FilterExample(true, "title", '%' + searchString + '%', InequalityConstants.LIKE, true));
		}
		model = new OrtTypeModel(filters, service);
		
	}
	
	public OrtTypeModel getModel() {
		return model;
	}
	
	public void setModel(OrtTypeModel model) {
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
		model = (OrtTypeModel) session.getAttribute("model");
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

	public OrtType getOrtType() {
		return OrtType;
	}

	public void setOrtType(OrtType OrtType) {
		this.OrtType = OrtType;
	} 
}
