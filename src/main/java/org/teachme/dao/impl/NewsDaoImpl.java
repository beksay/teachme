package org.teachme.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.teachme.dao.NewsDao;
import org.teachme.domain.News;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class NewsDaoImpl extends GenericDaoImpl<News, Integer> implements NewsDao {

	public NewsDaoImpl(EntityManager entityManager, Event<News> eventSource) {
		super(entityManager, eventSource);
	}

}
