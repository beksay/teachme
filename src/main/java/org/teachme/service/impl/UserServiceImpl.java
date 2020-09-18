package org.teachme.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.teachme.dao.UserDao;
import org.teachme.dao.impl.UserDaoImpl;
import org.teachme.domain.User;
import org.teachme.service.UserService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(UserService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UserServiceImpl extends GenericServiceImpl<User, Integer, UserDao> implements UserService {

	@Override
	protected UserDao getDao() {
		return new UserDaoImpl(em, se);
	}

}
