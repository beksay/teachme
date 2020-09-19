package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.OrtTypeDao;
import org.ebilim.domain.OrtType;

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
