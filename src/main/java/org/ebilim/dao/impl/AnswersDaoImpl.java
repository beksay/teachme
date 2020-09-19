package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.AnswersDao;
import org.ebilim.domain.Answers;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class AnswersDaoImpl extends GenericDaoImpl<Answers, Integer> implements AnswersDao {

	public AnswersDaoImpl(EntityManager entityManager, Event<Answers> eventSource) {
		super(entityManager, eventSource);
	}

}
