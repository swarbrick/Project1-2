import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ReimbursementDaoImpl;
import dao.UserDaoImpl;



public class Project1Tester {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createAccountTest() {
		assertEquals("this is the create account test", 1, UserDaoImpl.createAccount("xyz", "xyz", "x", "z", "@xyz", 2));
		assertEquals("this is the create account test", 1, UserDaoImpl.affirmAccountByUsername("xyz"));
		assertEquals("this is the create account test", 1, UserDaoImpl.deleteAccountByUsername("xyz"));
	}
	
	@Test
	public void loginTest() {
		int id = UserDaoImpl.getAccount("abc", "abc").getId();
		assertEquals("this is the login test", id, UserDaoImpl.getSessionUser().getId());		
	}
	
	@Test
	public void logoutTest() {
		UserDaoImpl.endSession();
		assertEquals("this is the logout test", null, UserDaoImpl.getSessionUser());
	}
	
	@Test
	public void hashTest() {
		String hash = UserDaoImpl.getHash("abc", "abc");
		assertEquals("this is the get hash test", hash, UserDaoImpl.getPassword("abc"));
	}
	
	@Test
	public void createReimbursementTest() {
		assertEquals("this is the create reimbursement test", 1, ReimbursementDaoImpl.createReimbursement(33, 143, 4, null, null));
		assertEquals("this is the create reimbursement test", 1, ReimbursementDaoImpl.deleteMostRecentReimbursement());
	}

}
