package org.teachme.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.teachme.dao.OrtTitleDao;
import org.teachme.dao.impl.OrtTitleDaoImpl;
import org.teachme.domain.OrtTitle;
import org.teachme.service.OrtTitleService;

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
