package org.teachme.service;

import javax.ejb.Local;

import org.teachme.domain.News;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface NewsService extends GenericService<News, Integer> {

}
