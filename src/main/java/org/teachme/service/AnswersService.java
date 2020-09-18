package org.teachme.service;

import javax.ejb.Local;

import org.teachme.domain.Answers;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface AnswersService extends GenericService<Answers, Integer> {

}
