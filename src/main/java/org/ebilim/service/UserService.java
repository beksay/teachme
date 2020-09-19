package org.ebilim.service;

import javax.ejb.Local;

import org.ebilim.domain.User;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface UserService extends GenericService<User, Integer> {

}
