package models;

import java.util.ArrayList;

public class ReimbStatusDesc {
	int id;
	String desc;
	
	private static ArrayList<ReimbStatusDesc> reimbStatusDescriptions= new ArrayList<>();
	
	public ReimbStatusDesc(int id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public static ArrayList<ReimbStatusDesc> getReimbStatusDescriptions() {
		return reimbStatusDescriptions;
	}

	public static void setReimbStatusDescriptions(ArrayList<ReimbStatusDesc> reimbStatusDescriptions) {
		ReimbStatusDesc.reimbStatusDescriptions = reimbStatusDescriptions;
	}
	
	public static void generateReimbStatusReferences() {
		reimbStatusDescriptions.add(new ReimbStatusDesc(1, "pending"));
		reimbStatusDescriptions.add(new ReimbStatusDesc(2, "approved"));
		reimbStatusDescriptions.add(new ReimbStatusDesc(3, "denied"));
	}
}
