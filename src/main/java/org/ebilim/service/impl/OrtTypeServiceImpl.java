package org.ebilim.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.OrtTypeDao;
import org.ebilim.dao.impl.OrtTypeDaoImpl;
import org.ebilim.domain.OrtType;
import org.ebilim.service.OrtTypeService;

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
