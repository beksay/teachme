package org.ebilim.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.ebilim.singleton.Configuration;
import org.ebilim.util.MailSender;

/***
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ApplicationLifeCycleListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		MailSender.destroy();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		Configuration.getInstance();
	}

}
