package org.ebilim.service;

import java.io.IOException;

import javax.ejb.Local;

import org.ebilim.domain.Attachment;
import org.ebilim.dto.AttachmentBinaryDTO;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface AttachmentService extends GenericService<Attachment, Integer> {
	
	Attachment saveFromDTO(AttachmentBinaryDTO binary) throws IOException;

}
