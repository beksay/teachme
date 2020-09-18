package org.teachme.service;

import java.io.IOException;

import javax.ejb.Local;

import org.teachme.domain.Attachment;
import org.teachme.dto.AttachmentBinaryDTO;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface AttachmentService extends GenericService<Attachment, Integer> {
	
	Attachment saveFromDTO(AttachmentBinaryDTO binary) throws IOException;

}
