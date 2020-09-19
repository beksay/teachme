package org.ebilim.controller.dict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.ebilim.beans.FilterExample;
import org.ebilim.controller.BaseController;
import org.ebilim.model.NewsModel;
import org.ebilim.service.NewsService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@ManagedBean
@ViewScoped
public class NewsList extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -6100072166946495229L;
	@EJB
	private NewsService service;
	private NewsModel model;
	
	@PostConstruct
	private void init() {
		List<FilterExample> filters = new ArrayList<>();
		//if(loginUtil.getCurrentUser().getPerson().getEducationInstitution() !=null) filters.add(new FilterExample("educationInstitution", loginUtil.getCurrentUser().getPerson().getEducationInstitution(), InequalityConstants.EQUAL));
		model = new NewsModel(filters, service);
	}


	public NewsModel getModel() {
		return model;
	}
	
	public void setModel(NewsModel model) {
		this.model = model;
	}
	
	public NewsService getService() {
		return service;
	}
	
	public void setService(NewsService service) {
		this.service = service;
	}
	
    
}
