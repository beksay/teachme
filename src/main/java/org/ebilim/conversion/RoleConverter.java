package org.ebilim.conversion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.convert.FacesConverter;

import org.ebilim.dao.RoleDao;
import org.ebilim.domain.Role;
import org.ebilim.service.RoleService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@ManagedBean
@RequestScoped
@FacesConverter(value="roleConverter")
public class RoleConverter extends EntityConvertor<Role, Integer, RoleDao, RoleService> {

	@EJB
	private RoleService service;
	
	@Override
	protected RoleService getService() {
		return service;
	}

	@Override
	protected Integer getID(String key) {
		try {
			return Integer.parseInt(key);
		} catch(Exception e) {
			return null;
		}
	}

}
