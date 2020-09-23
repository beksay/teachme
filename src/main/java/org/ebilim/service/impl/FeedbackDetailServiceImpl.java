package org.ebilim.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.FeedbackDetailDao;
import org.ebilim.dao.impl.FeedbackDetailDaoImpl;
import org.ebilim.domain.FeedbackDetail;
import org.ebilim.service.FeedbackDetailService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(FeedbackDetailService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class FeedbackDetailServiceImpl extends GenericServiceImpl<FeedbackDetail, Integer, FeedbackDetailDao> implements FeedbackDetailService {

	@Override
	protected FeedbackDetailDao getDao() {
		return new FeedbackDetailDaoImpl(em, se);
	}

}
