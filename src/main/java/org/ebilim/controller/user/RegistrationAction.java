package org.ebilim.controller.user;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.ebilim.beans.Message;
import org.ebilim.conversations.Conversational;
import org.ebilim.domain.Person;
import org.ebilim.domain.Role;
import org.ebilim.domain.User;
import org.ebilim.enums.UserStatus;
import org.ebilim.service.PersonService;
import org.ebilim.service.RoleService;
import org.ebilim.service.UserService;
import org.ebilim.util.MailSender;
import org.ebilim.util.PasswordBuilder;
import org.ebilim.util.web.FacesMessages;
import org.ebilim.util.web.LoginUtil;
import org.ebilim.util.web.Messages;
import org.ebilim.validator.EntityValidator;


@Named
@ConversationScoped
public class RegistrationAction extends Conversational{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService service;
	@EJB
	private PersonService personService;
	@EJB
	private RoleService roleService;
	
	@Inject
	private EntityValidator validator;
	@Inject
	private LoginUtil loginUtil;
	
	private User user;
	private Person person;
	
	@PostConstruct
	public void initialize() {
		user = new User();
		person = new Person();
	}
    
	public String save() {
		System.out.println("user======================="+user);
		if(user == null){
			FacesMessages.addMessage(Messages.getMessage("invalidData"), Messages.getMessage("invalidData"), null);
			return null;
		}

		List<User> users = service.findByProperty("email", user.getEmail());
    	if(!users.isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("emailIsAlreadyExists"), null) );
			return null;
    	}
    	
    	if (person.getRequirement()==false) {
    		FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("requerementRequired"), null) );
			return null;
		}
    	
    	PasswordBuilder accountBuilder = new PasswordBuilder();
    	accountBuilder.digits(7);
	    
	    String personAccount = accountBuilder.build();
	    
	    List<Person> personAccountCheck = personService.findByProperty("account", personAccount);
    	if(!personAccountCheck.isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("tryAgain"), null) );
			return null;
    	}
 
	    person.setAccount(personAccount);
	    person.setMoney(new BigDecimal(0));
        
		Role addRole = roleService.findById(2, false);
		
		user.setRole(addRole);
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 90);
		
		user.setPerson(person);
		user.setStatus(UserStatus.ACTIVE);
		user.setCountFailed(0);
		user.setDatePasswordExpired(calendar.getTime());
		validator.validate(user);
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("user.template");
		String template = null;
		try {
			template = IOUtils.toString(stream, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PasswordBuilder builder = new PasswordBuilder();
	    builder.lowercase(2)
	            .uppercase(8)
	            .specials(2)
	            .digits(2)
	            .shuffle();
	    
	    String password = builder.build();
	    String body = MessageFormat.format(template, new Object[]{user.getEmail(), user.getEmail(), user.getPerson().getFullName(), password});
	    Message message = new Message();
		message.setEmail(user.getEmail());
		message.setSubject("e-Bilim");
		message.setContent(body);
		
		MailSender.getInstance().asyncSend(message);
        
	    try {
			user.setPassword(loginUtil.getHashPassword(password));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    
	    user.setPerson(user.getPerson().getId() == null ? personService.persist(user.getPerson()) : personService.merge(user.getPerson()));
	    
		if (user.getId() == null) {
			service.persist(user);
		} else {
			service.merge(user);
		}
		
		FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_INFO,  Messages.getMessage("messagesSendToEmail"), null) );
        
		user = new User();
		
		return "/view/public/thank_you.xhtml?faces-redirect=true";
	}
	
	private String listPublic() {
		return "/ebilim/view/user/login.xhtml?faces-redirect=true";
	}
	
	public String loginForm() {
		user = new User();
		person = new Person();
		return "/view/user/login.xhtml?faces-redirect=true";
	}
	
	public String returnHome() {
		user = null;
		return home();
	}
	
	public String returnLogin() {
		user = null;
		return listPublic();
	}
	
	 public String register() {
		closeConversation();
		user = new User();
		person = new Person();
    	return "/view/public/registration.xhtml";
    }
	
	private String home(){
		return "/home.xhtml";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}

}
