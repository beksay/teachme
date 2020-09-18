package org.teachme.service;

import javax.ejb.Local;

import org.teachme.domain.Person;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface PersonService extends GenericService<Person, Integer> {

}
