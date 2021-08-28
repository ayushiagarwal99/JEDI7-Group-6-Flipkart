/**
 * 
 */
package com.flipkart.bean;

import com.flipkart.constant.GenderConstant;
import com.flipkart.constant.RoleConstant;

/**
 * @author vivek
 *
 */
public abstract class User {
	private String userId;
	private String name;
	private GenderConstant gender;
	protected RoleConstant role;
	private String password;
	private String address;
	
	public User(String userId) {
		this.userId = userId;
	}
	public User(String userId, String name,RoleConstant role,String password ,GenderConstant gender, String address) {
		this.userId = userId;
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.password = password;
		this.address = address;
	}

	public User() {
		
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the gender
	 */
	public GenderConstant getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(GenderConstant gender) {
		this.gender = gender;
	}
	/**
	 * @return the role
	 */
	public RoleConstant getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(RoleConstant role) {
		this.role = role;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	
}
