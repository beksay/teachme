package org.ebilim.service;

import javax.ejb.Local;

import org.ebilim.domain.Person;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface PersonService extends GenericService<Person, Integer> {

}
