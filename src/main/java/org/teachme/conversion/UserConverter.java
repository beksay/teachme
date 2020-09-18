package org.teachme.conversion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.convert.FacesConverter;

import org.teachme.dao.UserDao;
import org.teachme.domain.User;
import org.teachme.service.UserService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@ManagedBean
@RequestScoped
@FacesConverter(value="userConverter")
public class UserConverter extends EntityConvertor<User, Integer, UserDao, UserService> {

	@EJB
	private UserService service;
	
	@Override
	protected UserService getService() {
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
