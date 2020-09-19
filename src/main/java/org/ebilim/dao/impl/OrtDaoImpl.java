package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.OrtDao;
import org.ebilim.domain.Ort;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class OrtDaoImpl extends GenericDaoImpl<Ort, Integer> implements OrtDao {

	public OrtDaoImpl(EntityManager entityManager, Event<Ort> eventSource) {
		super(entityManager, eventSource);
	}

}
