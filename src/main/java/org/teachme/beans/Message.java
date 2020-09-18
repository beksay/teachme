package org.teachme.beans;

/***
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Message {
	
	private String email;
	private String subject;
	private String content;

	public Message() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}