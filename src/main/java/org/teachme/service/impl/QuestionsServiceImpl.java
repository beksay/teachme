package org.teachme.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.teachme.dao.QuestionsDao;
import org.teachme.dao.impl.QuestionsDaoImpl;
import org.teachme.domain.Questions;
import org.teachme.service.QuestionsService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(QuestionsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class QuestionsServiceImpl extends GenericServiceImpl<Questions, Integer, QuestionsDao> implements QuestionsService {

	@Override
	protected QuestionsDao getDao() {
		return new QuestionsDaoImpl(em, se);
	}

}
