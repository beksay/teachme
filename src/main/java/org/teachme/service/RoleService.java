package org.teachme.service;

import javax.ejb.Local;

import org.teachme.domain.Role;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface RoleService extends GenericService<Role, Integer> {

}
