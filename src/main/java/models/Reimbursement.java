package models;

import java.sql.Blob;

public class Reimbursement {
	
	private int id;
	private int amt;
	private String submitted;
	private String resolved;
	private String description;
	private Blob receipt;
	private int author_id;
	private int resolver_id;
	private int status_id;
	private int type_id;

	public Reimbursement(int id, int amt, String submitted, String resolved, String description, int status_id, int type_id, int author_id) {
		super();
		this.id = id;
		this.amt = amt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.status_id = status_id;
		this.type_id = type_id;
		this.author_id = author_id;
	}

	public Reimbursement(int id, int amt, String submitted, String resolved, String description, Blob receipt,
			int author_id, int resolver_id, int status_id, int type_id) {
		super();
		this.id = id;
		this.amt = amt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmt() {
		return amt;
	}

	public void setAmt(int amt) {
		this.amt = amt;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getResolver_id() {
		return resolver_id;
	}

	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amt=" + amt + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + receipt + ", author_id=" + author_id
				+ ", resolver_id=" + resolver_id + ", status_id=" + status_id + ", type_id=" + type_id + "]";
	}

	
}
