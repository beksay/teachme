package org.teachme.model;

import java.util.List;

import org.teachme.beans.FilterExample;
import org.teachme.domain.News;
import org.teachme.service.NewsService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class NewsModel extends BaseModel<NewsService, News, Integer> {

	private static final long serialVersionUID = -4871106869905562775L;

	public NewsModel(List<FilterExample> filters, NewsService service) {
		super(filters, service);
	}
	
	@Override
	protected Integer getKey(String key) {
		return Integer.parseInt(key);
	}
	
}
