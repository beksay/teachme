package org.teachme.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.teachme.dao.OrtTypeDao;
import org.teachme.domain.OrtType;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class OrtTypeDaoImpl extends GenericDaoImpl<OrtType, Integer> implements OrtTypeDao {

	public OrtTypeDaoImpl(EntityManager entityManager, Event<OrtType> eventSource) {
		super(entityManager, eventSource);
	}

}
