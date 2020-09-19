package org.ebilim.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.RoleDao;
import org.ebilim.dao.impl.RoleDaoImpl;
import org.ebilim.domain.Role;
import org.ebilim.service.RoleService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(RoleService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer, RoleDao> implements RoleService {

	@Override
	protected RoleDao getDao() {
		return new RoleDaoImpl(em, se);
	}

}
