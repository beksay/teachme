package org.ebilim.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.QuestionsDao;
import org.ebilim.dao.impl.QuestionsDaoImpl;
import org.ebilim.domain.Questions;
import org.ebilim.service.QuestionsService;

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
