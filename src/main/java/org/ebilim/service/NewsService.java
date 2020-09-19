package org.ebilim.service;

import javax.ejb.Local;

import org.ebilim.domain.News;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface NewsService extends GenericService<News, Integer> {

}
