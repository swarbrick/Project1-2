package servlets;

import javax.servlet.http.HttpServletRequest;

import controllers.CreateAccountForwardingController;
import controllers.EmployeeForwardingController;
import controllers.LoginForwardingController;
import controllers.ManagerForwardingController;

public class ForwardingRequestHelper {

	public static String process(HttpServletRequest req) {
		
		
		System.out.println("THIS is the current URI active: "+req.getRequestURI());
		
		switch (req.getRequestURI()) {
		case "/Project1-2/login":
			System.out.println("in login case");
			return LoginForwardingController.loginPage(req); 
		case "/Project1-2/createAccount":
			System.out.println("in create account case");
			return CreateAccountForwardingController.createAccountPage(req);
		case "/Project1-2/employee":
			System.out.println("in employee case");
			return EmployeeForwardingController.employeePage(req);
		case "/Project1-2/managerViewAll":
			System.out.println("in view all case");
			return ManagerForwardingController.viewAllPage(req);
		case "/Project1-2/managerViewApproved":
			System.out.println("in approved case");
			return ManagerForwardingController.viewApprovedPage(req);
		case "/Project1-2/managerViewDenied":
			System.out.println("in denied case");
			return ManagerForwardingController.viewDeniedPage(req);
		case "/Project1-2/managerViewPending":
			System.out.println("in pending case");
			return ManagerForwardingController.viewPendingPage(req);
		default:
			return "/html/login.html";
		}
	}

}
