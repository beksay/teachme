package org.teachme.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.teachme.dao.AnswersDao;
import org.teachme.dao.impl.AnswersDaoImpl;
import org.teachme.domain.Answers;
import org.teachme.service.AnswersService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(AnswersService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AnswersServiceImpl extends GenericServiceImpl<Answers, Integer, AnswersDao> implements AnswersService {

	@Override
	protected AnswersDao getDao() {
		return new AnswersDaoImpl(em, se);
	}

}
