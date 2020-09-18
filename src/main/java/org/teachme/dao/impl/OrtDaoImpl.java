package org.teachme.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.teachme.dao.OrtDao;
import org.teachme.domain.Ort;

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
