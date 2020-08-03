package service;

import dao.UserDaoImpl;
import models.User;

public class UserService {
	public static int createAccount(String ausername, String apassword, String fn, String ln, String email, int acctTypeId) {
		return UserDaoImpl.createAccount(ausername, apassword, fn, ln, email, acctTypeId);
	}

	public static User getAccount(String ausername, String apassword) {
		return UserDaoImpl.getAccount(ausername, apassword);
	}
	
	public static void endSession() {
		UserDaoImpl.endSession();;
	}
}
