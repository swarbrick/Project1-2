package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.apache.log4j.Logger;

import models.User;

public class UserDaoImpl {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static User sessionUser;
	final static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	private static String url=System.getenv("PROJECT_0_URL");
	private static String username=System.getenv("PROJECT_0_USERNAME");
	private static String password=System.getenv("PROJECT_0_PASSWORD");
		
	public static User getSessionUser() {
		logger.info("get current session user");
		return sessionUser;
	}

	public static void setSessionUser(User sessionUser) {
		logger.info("set session user");
		UserDaoImpl.sessionUser = sessionUser;
	}
	
	public static void endSession() {
		logger.info("logout");
		sessionUser = null;
	}

	public static int createAccount(String ausername, String apassword, String fn, String ln, String email, int acctTypeId) {
		logger.info("create account");
		int num = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ call insert_user_null_id(?,?,?,?,?,?) }";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1,  ausername);
			cs.setString(2,  apassword);
			cs.setString(3,  fn);
			cs.setString(4,  ln);
			cs.setString(5,  email);
			cs.setInt(6,  acctTypeId);

			num = cs.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public static User getAccount(String ausername, String apassword) {
		logger.info("login");
		User acct = null;
		
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT ers_users_id, user_first_name, user_last_name, user_email, user_role_id FROM ers_users WHERE ers_username = ? AND ers_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ausername);
			ps.setString(2, getHash(ausername, apassword));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				acct = new User(rs.getInt("ers_users_id"), rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), rs.getInt("user_role_id"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		sessionUser = acct;
		System.out.println(acct);
		return acct;
	}
	
	public static String getHash(String ausername, String apassword) {
		logger.info("get hash");
		String hash = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "{ ? = call get_customer_hash(?,?) }";
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2,  ausername);
			cs.setString(3,  apassword);
			cs.execute();
			hash = cs.getString(1);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hash;
	}	
	
	public static int affirmAccountByUsername(String ausername) {
		int num = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_users WHERE ers_username = ?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, ausername);
			num = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static int deleteAccountByUsername(String ausername) {
		int num = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "DELETE FROM ers_users WHERE ers_username = ?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, ausername);
			num = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static String getPassword(String ausername) {
		String p = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT ers_password FROM ers_users WHERE ers_username = ?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, ausername);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				p = rs.getString("ers_password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
}
