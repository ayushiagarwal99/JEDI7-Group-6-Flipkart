/**
 * 
 */
package com.flipkart.bean;

import java.util.Date;

import com.flipkart.constant.GenderConstant;
import com.flipkart.constant.RoleConstant;

/**
 * @author vivek
 *
 */
public class Professor extends User {
	private String professorID;
	private String department;
	private String designation;
	
	
	public Professor(String userID) {
		super(userID);
	}
	public Professor(String userID, String name, GenderConstant gender, RoleConstant role, String password, String address) {
		super(userID, name, role, password, gender, address);
	}
	public Professor() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the professorID
	 */
	public String getProfessorID() {
		return professorID;
	}
	/**
	 * @param professorID the professorID to set
	 */
	public void setProfessorID(String professorID) {
		this.professorID = professorID;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the dateOfJoining
	 */
	
}
