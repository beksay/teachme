package org.ebilim.controller.ort;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ebilim.conversations.ConversationOrt;
import org.ebilim.domain.Ort;
import org.ebilim.service.OrtService;
import org.ebilim.util.web.Messages;
import org.primefaces.event.SelectEvent;


@Named
@ViewScoped
public class OrtController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private OrtService service;
	@Inject
	private ConversationOrt conversation;	
	
	private Ort ort;
    
	@PostConstruct
	public void init() {
		ort=conversation.getOrt();
		if (ort==null) ort= new Ort();
	}
	
	public String add() {
		ort = new Ort();
		conversation.setOrt(ort);
		return form();
	}
	
	public String edit(Ort ort) {
		this.ort = ort;
		conversation.setOrt(ort);
		return form();
	}
	
	public String save() {
		if(ort == null){
			FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("invalidData"), null) );
			return null;
		}
		if(ort.getId()==null){
			ort = service.persist(ort);
		}
		else{
			ort = service.merge(ort);
		}
		
		conversation.setOrt(null);
		conversation.closeConversation();
	    return cancel();  	
	}

	public void onRowSelect(SelectEvent event) throws IOException {
		ort=(Ort) event.getObject();
		conversation.setOrt(ort);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ebilim/view/ort/ort_type_list.xhtml?cid="+conversation.getId());
        
    }
	
	public void onRowSelectTest(SelectEvent event) throws IOException {
		ort=(Ort) event.getObject();
		conversation.setOrt(ort);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ebilim/view/ort_test/ort_type_list.xhtml?cid="+conversation.getId());
        
    }
	
	public String delete(Ort c) {
		service.remove(c);
		return cancel();
	}
	
	public String cancel() {
		ort = null;
		return list();
	}
	
	private String list() {
		return "/view/ort/ort_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/ort/ort_form.xhtml";
	}

	public Ort getOrt() {
		return ort;
	}
	
	public void setOrt(Ort ort) {
		this.ort = ort;
	}

}
