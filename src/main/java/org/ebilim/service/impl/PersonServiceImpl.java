package org.ebilim.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.PersonDao;
import org.ebilim.dao.impl.PersonDaoImpl;
import org.ebilim.domain.Person;
import org.ebilim.service.PersonService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(PersonService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PersonServiceImpl extends GenericServiceImpl<Person, Integer, PersonDao> implements PersonService {

	@Override
	protected PersonDao getDao() {
		return new PersonDaoImpl(em, se);
	}

}
