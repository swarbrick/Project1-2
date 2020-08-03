package controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class EmployeeForwardingController {
	
	final static Logger logger = Logger.getLogger(EmployeeForwardingController.class);

	public static String employeePage(HttpServletRequest req) {
		logger.info("delivering employee view");
		return "/html/employee.html";
	}

}
