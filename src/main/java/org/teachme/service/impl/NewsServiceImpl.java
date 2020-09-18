package org.teachme.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.teachme.dao.NewsDao;
import org.teachme.dao.impl.NewsDaoImpl;
import org.teachme.domain.News;
import org.teachme.service.NewsService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(NewsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class NewsServiceImpl extends GenericServiceImpl<News, Integer, NewsDao> implements NewsService {

	@Override
	protected NewsDao getDao() {
		return new NewsDaoImpl(em, se);
	}

}
