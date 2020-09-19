package org.ebilim.controller.user;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.ebilim.beans.FilterExample;
import org.ebilim.beans.InequalityConstants;
import org.ebilim.beans.Message;
import org.ebilim.domain.User;
import org.ebilim.enums.ScopeConstants;
import org.ebilim.enums.SortEnum;
import org.ebilim.enums.UserStatus;
import org.ebilim.service.UserService;
import org.ebilim.util.MailSender;
import org.ebilim.util.PasswordBuilder;
import org.ebilim.util.web.FacesScopeQualifier;
import org.ebilim.util.web.LoginUtil;
import org.ebilim.util.web.Messages;
import org.ebilim.util.web.ScopeQualifier;


/***
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@ManagedBean
@RequestScoped
public class UserController {
	@EJB
	private UserService userService;
    private String password;
    private String email;
    private String lastname;
    
    @Inject
    private LoginUtil loginUtil;

    public String login() throws Exception{
    	if(email.equals("") ) {
    		return null;
		} else if ( password.equals("") ) {
			return null;
		}
		
    	System.out.println("login:" + email);
    	
		String hashPassword = loginUtil.getHashPassword(password);
    	
    	System.out.println("password:" + password + " hash = " + hashPassword);
    	
    	List<User> users = userService.findByProperty("email", email);
    	if(users.isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("emailIsIncorrect"), null) );
			return null;
    	}
    	
		List<FilterExample> examples = new ArrayList<FilterExample>();
		examples.add(new FilterExample("email", email, InequalityConstants.EQUAL, true));	
		examples.add(new FilterExample("password", hashPassword, InequalityConstants.EQUAL));
		
		List<User> userList = userService.findByExample(0, 1, SortEnum.ASCENDING, examples, "id");
		System.out.println("userList: " + userList);
		
		if(userList.isEmpty()){
			User user = users.get(0);
			user.setCountFailed(user.getCountFailed() == null ? 1 : user.getCountFailed() + 1);
			userService.merge(user);
			
			if(user.getCountFailed() >= 5){
				user.setStatus(UserStatus.BLOCKED);
				userService.merge(user);
				
				FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("userIsNotActive"), null) );
				FacesContext.getCurrentInstance().getExternalContext().redirect("/ebilim/view/user/blocked.xhtml");
			}
			
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage(FacesMessage.SEVERITY_ERROR,  Messages.getMessage("emailOrPasswordIncorrect"), null) );
			return null;
		}
			
		User user = userList.get(0);
		
		if(user.getStatus() == null || user.getStatus().equals(UserStatus.INACTIVE) || user.getStatus().equals(UserStatus.BLOCKED)){
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("userIsNotActive"), null) );
			FacesContext.getCurrentInstance().getExternalContext().redirect("/ebilim/view/user/blocked.xhtml");
			
			return null;
		}
		
		loginUtil.setCurrentUser(user);
		
		if(user.getDatePasswordExpired() == null || user.getDatePasswordExpired().getTime()  <= System.currentTimeMillis()){
			ScopeQualifier qualifier = new FacesScopeQualifier();
			qualifier.setValue("changePassword", true, ScopeConstants.SESSION_SCOPE);
			return "/view/user/change_password.xhtml";
		}
		
		String address = loginUtil.userHasRole(user, "ck") ? "/ebilim/view/main.xhtml" : "/ebilim/view/main.xhtml";
		
		user.setCountFailed(0);
		userService.merge(user);
		
    	FacesContext.getCurrentInstance().getExternalContext().redirect(address);
		return address;
    }
    
    public String restore() throws Exception{
    	if(email.equals("") ) {
    		return null;
		} else if ( email.equals("") ) {
			return null;
		}
		
    	System.out.println("email =====" + email);
    	
    	List<User> users = userService.findByProperty("email", email);
    	if(users.isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("emailIsIncorrect"), null) );
			return null;
    	}
    	
    	List<FilterExample> examples = new ArrayList<FilterExample>();
		examples.add(new FilterExample("email", email, InequalityConstants.EQUAL));	
		examples.add(new FilterExample("person.firstName", lastname, InequalityConstants.EQUAL));
		
		List<User> userList = userService.findByExample(0, 1, SortEnum.ASCENDING, examples, "id");
		System.out.println("userList: " + userList);
		
		if(userList.isEmpty()){
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage(FacesMessage.SEVERITY_ERROR,  Messages.getMessage("emailOrLastnameIncorrect"), null) );
			return null;
		}
    	

    	InputStream stream = this.getClass().getClassLoader().getResourceAsStream("user.template");
		String template = null;
		try {
			template = IOUtils.toString(stream, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		User user = userList.get(0);
		user=userService.findByProperty("id", user.getId()).get(0);
		
		PasswordBuilder builder = new PasswordBuilder();
	    builder.lowercase(2)
	            .uppercase(8)
	            .specials(2)
	            .digits(2)
	            .shuffle();
	    
	    String password = builder.build();
		
		String body = MessageFormat.format(template, new Object[]{email, email, user.getPerson().getFullName(), password});
		user.setDatePasswordExpired(new Date());
		user.setPassword(loginUtil.getHashPassword(password));
		user.setCountFailed(0);
		user.setStatus(UserStatus.ACTIVE);
		userService.merge(user);
		
		Message message = new Message();
		message.setEmail(email);
		message.setSubject("Jukbar");
		message.setContent(body);
		
		MailSender.getInstance().asyncSend(message);
        
		FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_INFO,  Messages.getMessage("messagesSend"), null) );
        
        System.out.println("finished");
        return "/view/public/password_send.xhtml";
    }
    
    public String logout() {
		loginUtil.logout();
		return "/view/user/login.xhtml";
	}
    
    public String forgot() {
    	return "/ebilim/view/user/forgot_password.xhtml";
    }
    
    public String back() {
    	return "/ebilim/view/user/login.xhtml";
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
