package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import models.Reimbursement;

public class ReimbursementDaoImpl {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String url=System.getenv("PROJECT_0_URL");
	private static String username=System.getenv("PROJECT_0_USERNAME");
	private static String password=System.getenv("PROJECT_0_PASSWORD");
	
	final static Logger logger = Logger.getLogger(ReimbursementDaoImpl.class);

	

	public static int createReimbursement(int amt, int authorId, int typeId, String description, Blob receipt) {
		logger.info("create reimbursement");
		int success = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_author, reimb_type_id, reimb_description, reimb_receipt) VALUES (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, amt);		
			ps.setInt(2, authorId);			
			ps.setInt(3, typeId);
			ps.setString(4, description);
			ps.setBlob(5, receipt);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public static ArrayList<Reimbursement> getEmployeeReimbursements(int empId) {
		logger.info("get employee reimbursements");
		ArrayList<Reimbursement> list = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_status_id, reimb_type_id FROM ers_reimbursement WHERE reimb_author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_amount"), rs.getString("reimb_submitted"), rs.getString("reimb_resolved"), rs.getString("reimb_description"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"), rs.getInt("reimb_author")));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return list;
	}
	
	public static ArrayList<Reimbursement> getAllReimbursements() {
		logger.info("get all reimbursements");
		ArrayList<Reimbursement> list = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_amount"), rs.getString("reimb_submitted"), rs.getString("reimb_resolved"), rs.getString("reimb_description"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"), rs.getInt("reimb_author")));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return list;
	}
	
	public static ArrayList<Reimbursement> getReimbursementsByStatus(int statusId) {
		logger.info("get reimbursements by status: " + statusId);
		ArrayList<Reimbursement> list = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, statusId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_amount"), rs.getString("reimb_submitted"), rs.getString("reimb_resolved"), rs.getString("reimb_description"), rs.getBlob("reimb_receipt"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return list;
	}
	
	
	public static int resolveReimbursement(int reimbId, int statusId) {
		logger.info("resolve reimbursement");
		int success = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "UPDATE ers_reimbursement SET reimb_status_id = ? WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, statusId);		
			ps.setInt(2, reimbId);			
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public static int deleteMostRecentReimbursement() {
		int success = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "DELETE FROM ers_reimbursement WHERE reimb_id = (SELECT MAX(reimb_id) FROM ers_reimbursement)";
			PreparedStatement ps = conn.prepareStatement(sql);			
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
}
