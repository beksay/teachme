package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.FeedbackDetailDao;
import org.ebilim.domain.FeedbackDetail;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class FeedbackDetailDaoImpl extends GenericDaoImpl<FeedbackDetail, Integer> implements FeedbackDetailDao {

	public FeedbackDetailDaoImpl(EntityManager entityManager, Event<FeedbackDetail> eventSource) {
		super(entityManager, eventSource);
	}

}
