package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.OrtTitleDao;
import org.ebilim.domain.OrtTitle;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class OrtTitleDaoImpl extends GenericDaoImpl<OrtTitle, Integer> implements OrtTitleDao {

	public OrtTitleDaoImpl(EntityManager entityManager, Event<OrtTitle> eventSource) {
		super(entityManager, eventSource);
	}

}
