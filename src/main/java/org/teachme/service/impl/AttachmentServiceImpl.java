package org.teachme.service.impl;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.teachme.dao.AttachmentDao;
import org.teachme.dao.impl.AttachmentDaoImpl;
import org.teachme.domain.Attachment;
import org.teachme.dto.AttachmentBinaryDTO;
import org.teachme.dto.AttachmentDataSource;
import org.teachme.dto.IdentifyResponse;
import org.teachme.service.AttachmentService;
import org.teachme.soa.RepositoryService;
import org.teachme.soa.RepositoryServiceFactory;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(AttachmentService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AttachmentServiceImpl extends GenericServiceImpl<Attachment, Integer, AttachmentDao> implements AttachmentService {

	private RepositoryService service;
	
	@PostConstruct
	private void init(){
		try {
			service = RepositoryServiceFactory.getInstance().getService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected AttachmentDao getDao() {
		return new AttachmentDaoImpl(em,se);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Attachment saveFromDTO(AttachmentBinaryDTO binary) throws IOException {
		binary.setName(binary.getAttachment().getRepositoryName());
		DataHandler handler = new DataHandler(new AttachmentDataSource(binary));
		IdentifyResponse response = service.save(binary.getAttachment().getFileName(), handler);
		binary.getAttachment().setRepositoryName(response.getChecksum());
		
		return persist(binary.getAttachment());
	}

}
