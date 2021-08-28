package com.flipkart.constant;

/**
 * 
 * @author Aditya
 * Enumeration class for RoleConstant Types
 *
 */
public enum RoleConstant {
	ADMIN,PROFESSOR,STUDENT;
	

	@Override
	public String toString()
	{
		final String name=name();
		return name; 
	}
	
	/**
	 * Method to get RoleConstant object from String
	 * @param role
	 * @return RoleConstant object
	 */
	public static RoleConstant stringToName(String role)
	{
		RoleConstant userRole=null;

		if(role.equalsIgnoreCase("ADMIN"))
			userRole=RoleConstant.ADMIN;
		else if(role.equalsIgnoreCase("PROFESSOR"))
			userRole=RoleConstant.PROFESSOR;
		else if(role.equalsIgnoreCase("STUDENT"))
			userRole=RoleConstant.STUDENT;
		return userRole;
	}
}