package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.AttachmentDao;
import org.ebilim.domain.Attachment;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class AttachmentDaoImpl extends GenericDaoImpl<Attachment, Integer> implements AttachmentDao {

	public AttachmentDaoImpl(EntityManager entityManager, Event<Attachment> eventSource) {
		super(entityManager, eventSource);
	}

}
