package controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class CreateAccountForwardingController {

	final static Logger logger = Logger.getLogger(CreateAccountForwardingController.class);

	public static String createAccountPage(HttpServletRequest req) {
			logger.info("delivering create account view");
			return "/html/createAccount.html";
	}

}
