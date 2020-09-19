package org.ebilim.model;

import java.util.List;

import org.ebilim.beans.FilterExample;
import org.ebilim.domain.User;
import org.ebilim.service.UserService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class UserModel extends BaseModel<UserService, User, Integer> {

	private static final long serialVersionUID = -4871106869905562775L;

	public UserModel(List<FilterExample> filters, UserService service) {
		super(filters, service);
	}
	
	@Override
	protected Integer getKey(String key) {
		return Integer.parseInt(key);
	}
	
}
