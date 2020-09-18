package org.teachme.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.teachme.dao.FeedbackDao;
import org.teachme.dao.impl.FeedbackDaoImpl;
import org.teachme.domain.Feedback;
import org.teachme.service.FeedbackService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(FeedbackService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class FeedbackServiceImpl extends GenericServiceImpl<Feedback, Integer, FeedbackDao> implements FeedbackService {

	@Override
	protected FeedbackDao getDao() {
		return new FeedbackDaoImpl(em, se);
	}

}
