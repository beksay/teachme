package org.ebilim.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.FeedbackDao;
import org.ebilim.dao.impl.FeedbackDaoImpl;
import org.ebilim.domain.Feedback;
import org.ebilim.service.FeedbackService;

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
