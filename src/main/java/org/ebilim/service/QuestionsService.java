package org.ebilim.service;

import javax.ejb.Local;

import org.ebilim.domain.Questions;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface QuestionsService extends GenericService<Questions, Integer> {

}
