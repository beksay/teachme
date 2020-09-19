package org.ebilim.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.MyAnswerDao;
import org.ebilim.dao.impl.MyAnswerDaoImpl;
import org.ebilim.domain.MyAnswer;
import org.ebilim.service.MyAnswerService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(MyAnswerService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MyAnswerServiceImpl extends GenericServiceImpl<MyAnswer, Integer, MyAnswerDao> implements MyAnswerService {

	@Override
	protected MyAnswerDao getDao() {
		return new MyAnswerDaoImpl(em, se);
	}

}
