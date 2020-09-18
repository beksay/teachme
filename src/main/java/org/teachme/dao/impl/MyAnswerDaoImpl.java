package org.teachme.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.teachme.dao.MyAnswerDao;
import org.teachme.domain.MyAnswer;

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
