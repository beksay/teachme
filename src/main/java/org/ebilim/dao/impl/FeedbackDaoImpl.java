package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.FeedbackDao;
import org.ebilim.domain.Feedback;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class FeedbackDaoImpl extends GenericDaoImpl<Feedback, Integer> implements FeedbackDao {

	public FeedbackDaoImpl(EntityManager entityManager, Event<Feedback> eventSource) {
		super(entityManager, eventSource);
	}

}
