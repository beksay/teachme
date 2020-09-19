package org.ebilim.controller.ort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ebilim.beans.FilterExample;
import org.ebilim.beans.InequalityConstants;
import org.ebilim.conversations.ConversationOrt;
import org.ebilim.conversations.ConversationOrtType;
import org.ebilim.domain.OrtTitle;
import org.ebilim.domain.OrtType;
import org.ebilim.service.OrtService;
import org.ebilim.service.OrtTitleService;
import org.ebilim.service.OrtTypeService;
import org.ebilim.util.web.Messages;


@Named
@ViewScoped
public class OrtTypeController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private OrtTypeService service;
	@EJB
	private OrtService ortService;
	@EJB
	private OrtTitleService ortTitleService;
	@Inject
	private ConversationOrtType conversation;
	@Inject
	private ConversationOrt conversationOrt;
	
	private OrtType ortType;
    
	@PostConstruct
	public void init() {
		ortType=conversation.getOrtType();
		if (ortType==null) ortType= new OrtType();
	}
	
	public String add() {
		ortType = new OrtType();
		conversation.setOrtType(ortType);
		return form();
	}
	
	public String edit(OrtType ortType) {
		this.ortType = ortType;
		conversation.setOrtType(ortType);
		return form();
	}
	
	public String save() {
		if(ortType == null){
			FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("invalidData"), null) );
			return null;
		}
		ortType.setOrt(conversationOrt.getOrt());
		if(ortType.getId()==null){
			ortType = service.persist(ortType);
		}
		else{
			ortType = service.merge(ortType);
		}
		
		conversation.setOrtType(null);
	    return cancel();  	
	}

    public List<OrtTitle> getOrtTitleList() {
		
		List<Integer> selectedPC = new ArrayList<>();
		if(conversationOrt !=null) {
			System.out.println("ortType====" + ortType);
			List<FilterExample> filters = new ArrayList<>();
		    filters.add(new FilterExample("ort",conversationOrt.getOrt(),InequalityConstants.EQUAL));
		    List<OrtType> scList = service.findByExample(0, 100, filters);
			for (OrtType entity : scList) {
				selectedPC.add(entity.getOrtTitle().getId());
			}
		}	
		
		List<FilterExample> examples = new ArrayList<>();
	
		if (selectedPC.size()>0) examples.add(new FilterExample("id", selectedPC, InequalityConstants.NOT_IN));	
		
		return ortTitleService.findByExample(0, 100, examples);
	}
	
	public String delete(OrtType c) {
		service.remove(c);
		return cancel();
	}
	
	public String cancel() {
		ortType = null;
		return list();
	}
	
	public String view(OrtType ortType) {
		this.ortType = ortType;
		conversation.setOrtType(ortType);
		return formQuestions();
	}
	
	public String viewTest(OrtType ortType) {
		this.ortType = ortType;
		conversation.setOrtType(ortType);
		return "/view/ort_test/questions_list.xhtml";
	}
	
	private String list() {
		return "/view/ort/ort_type_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/ort/ort_type_form.xhtml";
	}

	private String formQuestions() {
		return "/view/ort/questions_list.xhtml";
	}

	public OrtType getOrtType() {
		return ortType;
	}

	public void setOrtType(OrtType ortType) {
		this.ortType = ortType;
	}

	

}
