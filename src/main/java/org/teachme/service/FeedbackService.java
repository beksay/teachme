package org.teachme.service;

import javax.ejb.Local;

import org.teachme.domain.Feedback;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface FeedbackService extends GenericService<Feedback, Integer> {

}
