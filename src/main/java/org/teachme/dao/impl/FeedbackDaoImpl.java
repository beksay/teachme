package org.teachme.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.teachme.dao.FeedbackDao;
import org.teachme.domain.Feedback;

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
