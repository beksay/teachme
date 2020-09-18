package org.teachme.service;

import javax.ejb.Local;

import org.teachme.domain.MyAnswer;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface MyAnswerService extends GenericService<MyAnswer, Integer> {

}
