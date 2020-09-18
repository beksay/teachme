package org.teachme.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.xml.bind.DatatypeConverter;

/***
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Krypto {
	
	private SecretKey key;
	private final Cipher cipher;
	private final SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
	
	public Krypto() throws Exception {
		cipher = Cipher.getInstance("DES");
	}
	
	public void setKey(byte[] bytes) throws Exception {
		DESKeySpec dks = new DESKeySpec(bytes);
		key = skf.generateSecret(dks);
	}
	
	public String doEncrypt(byte[] bytes) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] enBytes = cipher.doFinal(bytes);
		
		return DatatypeConverter.printBase64Binary(enBytes);
	}
	
	public byte[] doDecrypt(String encodeWord) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] bytes = DatatypeConverter.parseBase64Binary(encodeWord);
		
		return cipher.doFinal(bytes);
	}
	
}