package org.teachme.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.teachme.dao.RoleDao;
import org.teachme.domain.Role;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao {

	public RoleDaoImpl(EntityManager entityManager, Event<Role> eventSource) {
		super(entityManager, eventSource);
	}

}
