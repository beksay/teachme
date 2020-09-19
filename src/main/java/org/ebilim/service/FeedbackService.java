package org.ebilim.service;

import javax.ejb.Local;

import org.ebilim.domain.Feedback;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface FeedbackService extends GenericService<Feedback, Integer> {

}
