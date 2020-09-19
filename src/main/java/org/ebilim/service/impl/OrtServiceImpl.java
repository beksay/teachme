package org.ebilim.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.OrtDao;
import org.ebilim.dao.impl.OrtDaoImpl;
import org.ebilim.domain.Ort;
import org.ebilim.service.OrtService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(OrtService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OrtServiceImpl extends GenericServiceImpl<Ort, Integer, OrtDao> implements OrtService {

	@Override
	protected OrtDao getDao() {
		return new OrtDaoImpl(em, se);
	}

}
