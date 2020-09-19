package org.ebilim.service;

import javax.ejb.Local;

import org.ebilim.domain.Role;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface RoleService extends GenericService<Role, Integer> {

}
