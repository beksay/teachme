package org.ebilim.data;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.ebilim.beans.FilterExample;
import org.ebilim.domain.PersistentEntity;
import org.ebilim.enums.SortEnum;
import org.ebilim.service.GenericService;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public abstract class BaseData<S extends GenericService<E, K>, E extends PersistentEntity<K>, K extends Serializable> {

    @EJB
    private S service;

    protected List<E> entities;
    protected List<FilterExample> list;
    
    protected abstract void initFilter();
    
    public List<E> getAll(){
    	return entities;
    };
		
    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
    	initFilter();
        entities = service.findByExample(0, 100000, SortEnum.DESCENDING, list, "id");
    }
    
    protected S getService() {
		return service;
	}
}
