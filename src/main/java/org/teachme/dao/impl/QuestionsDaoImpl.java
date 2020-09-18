package org.teachme.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.teachme.dao.QuestionsDao;
import org.teachme.domain.Questions;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class QuestionsDaoImpl extends GenericDaoImpl<Questions, Integer> implements QuestionsDao {

	public QuestionsDaoImpl(EntityManager entityManager, Event<Questions> eventSource) {
		super(entityManager, eventSource);
	}

}
