package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.UserDao;
import org.ebilim.domain.User;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	public UserDaoImpl(EntityManager entityManager, Event<User> eventSource) {
		super(entityManager, eventSource);
	}

}
