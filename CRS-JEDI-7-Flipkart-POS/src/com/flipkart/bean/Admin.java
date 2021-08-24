package com.flipkart.bean;

public class Admin extends User{
	private int AdminId;
	
	public Admin() {}

	public Admin(int userId, String password, String name, String address, String emailID, int adminId) {
		super(userId, password, name, address, emailID);
		AdminId = adminId;
	}

	public int getAdminId() {
		return AdminId;
	}

	public void setAdminId(int adminId) {
		AdminId = adminId;
	}
	
}
