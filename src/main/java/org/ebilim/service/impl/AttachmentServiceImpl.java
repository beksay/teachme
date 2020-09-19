package org.ebilim.service.impl;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ebilim.dao.AttachmentDao;
import org.ebilim.dao.impl.AttachmentDaoImpl;
import org.ebilim.domain.Attachment;
import org.ebilim.dto.AttachmentBinaryDTO;
import org.ebilim.dto.AttachmentDataSource;
import org.ebilim.dto.IdentifyResponse;
import org.ebilim.service.AttachmentService;
import org.ebilim.soa.RepositoryService;
import org.ebilim.soa.RepositoryServiceFactory;

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
