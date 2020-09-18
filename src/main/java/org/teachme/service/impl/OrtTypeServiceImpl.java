package org.teachme.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.teachme.dao.OrtTypeDao;
import org.teachme.dao.impl.OrtTypeDaoImpl;
import org.teachme.domain.OrtType;
import org.teachme.service.OrtTypeService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(OrtTypeService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OrtTypeServiceImpl extends GenericServiceImpl<OrtType, Integer, OrtTypeDao> implements OrtTypeService {

	@Override
	protected OrtTypeDao getDao() {
		return new OrtTypeDaoImpl(em, se);
	}

}
