package org.ebilim.service;

import javax.ejb.Local;

import org.ebilim.domain.MyAnswer;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface MyAnswerService extends GenericService<MyAnswer, Integer> {

}
