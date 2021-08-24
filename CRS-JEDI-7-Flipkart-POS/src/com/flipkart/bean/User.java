package com.flipkart.bean;

public abstract class User {
    private int userId;
    private String password;
    private String name;
    private String address;
    private String emailID;

    public User() {
        super();
    }

    public User(int userId, String password, String name, String address, String emailID) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.address = address;
		this.emailID = emailID;
	}

    public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

    public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}