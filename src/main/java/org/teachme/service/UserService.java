package org.teachme.service;

import javax.ejb.Local;

import org.teachme.domain.User;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface UserService extends GenericService<User, Integer> {

}
