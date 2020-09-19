package org.ebilim.util.web;

import javax.servlet.http.HttpServletRequest;

import org.ebilim.singleton.Configuration;

/***
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class HttpUtil {
	
	public static String getContextUrl(HttpServletRequest req) {
		return Configuration.getInstance().getProperty("address");		
	}

}
