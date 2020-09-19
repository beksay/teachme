package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.MyAnswerDao;
import org.ebilim.domain.MyAnswer;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class MyAnswerDaoImpl extends GenericDaoImpl<MyAnswer, Integer> implements MyAnswerDao {

	public MyAnswerDaoImpl(EntityManager entityManager, Event<MyAnswer> eventSource) {
		super(entityManager, eventSource);
	}

}
