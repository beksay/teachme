package org.ebilim.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.OrtTitleDao;
import org.ebilim.dao.impl.OrtTitleDaoImpl;
import org.ebilim.domain.OrtTitle;
import org.ebilim.service.OrtTitleService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(OrtTitleService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OrtTitleServiceImpl extends GenericServiceImpl<OrtTitle, Integer, OrtTitleDao> implements OrtTitleService {

	@Override
	protected OrtTitleDao getDao() {
		return new OrtTitleDaoImpl(em, se);
	}

}
