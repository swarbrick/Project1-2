package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import controllers.ReimbursementAJAXController;
import controllers.UserAJAXController;

public class AJAXRequestHelper {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {

		System.out.println("THIS is the current URI active: "+req.getRequestURI());
		
		switch (req.getRequestURI()) {
		case "/Project1-2/doCreateAccount":
			System.out.println("in do create account case");
			UserAJAXController.doCreateAccount(req, res); 
			break;
		case "/Project1-2/getAccount":
			System.out.println("in get account case");
			UserAJAXController.getAccount(req, res);
			break;
		case "/Project1-2/doCreateReimbursement":
			System.out.println("in create reimbursement case");
			ReimbursementAJAXController.doCreateReimbursement(req, res);
			break;
		case "/Project1-2/doResolveReimbursement":
			System.out.println("in resolve case");
			ReimbursementAJAXController.doResolveReimbursement(req, res);
			break;
		case "/Project1-2/getAllReimbursements":
			System.out.println("in get all case");
			ReimbursementAJAXController.getAll(req, res);
			break;
		case "/Project1-2/getApprovedReimbursements":
			System.out.println("in get approved case");
			ReimbursementAJAXController.getApproved(req, res);
			break;
		case "/Project1-2/getDeniedReimbursements":
			System.out.println("in get denied case");
			ReimbursementAJAXController.getDenied(req, res);
			break;
		case "/Project1-2/getPendingReimbursements":
			System.out.println("in get pending case");
			ReimbursementAJAXController.getPending(req, res);
			break;
		case "/Project1-2/getEmployeeReimbursements":
			System.out.println("in get employee reimbursements case");
			ReimbursementAJAXController.getEmployeeReimbursements(req, res);
			break;
		case "/Project1-2/logout":
			System.out.println("in logout case");
			UserAJAXController.logout(req, res);
			break;
		case "/Project1-2/getSessionType":
			System.out.println("in get session case");
			UserAJAXController.getSessionType(req, res);
			break;
		default:
			System.out.println("in default case");
		}
	}

}
