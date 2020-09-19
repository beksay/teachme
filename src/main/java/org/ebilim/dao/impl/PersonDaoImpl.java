package org.ebilim.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.ebilim.dao.PersonDao;
import org.ebilim.domain.Person;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class PersonDaoImpl extends GenericDaoImpl<Person, Integer> implements PersonDao {

	public PersonDaoImpl(EntityManager entityManager, Event<Person> eventSource) {
		super(entityManager, eventSource);
	}

}
