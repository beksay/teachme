package org.teachme.service;

import javax.ejb.Local;

import org.teachme.domain.Questions;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface QuestionsService extends GenericService<Questions, Integer> {

}
